package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ListFileController {
    @RequestMapping("/listFile")
    public String listFile(HttpServletRequest request, HttpServletResponse response){
        String uploadFilePath=request.getSession().getServletContext().getRealPath("/upload/");//上传文件夹
        System.out.println(uploadFilePath);
        Map<String,String> map=new HashMap<>();
        File file=new File(uploadFilePath);
        listfile(file,map);
        request.setAttribute("map",map);
        return "fileList";
    }

    public void listfile(File file,Map<String,String> map){
        //判断文件夹/文件是否存在
        if(!file.exists()){
            System.out.println(1111);
            map.put("eorrormsg","没有上传文件");
        }else {
            System.out.println(2222);
            //判断file对象是文件还是文件夹
            if (!file.isFile()){
                //如果是文件夹，则遍历文件夹中的所有文件及文件夹
                File[] files=file.listFiles();//返回某个目录下所有文件和目录的绝对路径，返回的是File数组
                for (File f:files){
                    listfile(f,map);
                }
            }else {
                System.out.println(file.getName());
                map.put(file.getName(),file.getName());
            }
        }
    }
}
