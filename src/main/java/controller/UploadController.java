package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@Controller
public class UploadController {
    @RequestMapping("/singleUpload")
    public String uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response){
        String filename=file.getOriginalFilename();// 原始文件名(在客户端时的文件名)
        String realPath=request.getSession().getServletContext().getRealPath("/");//服务器目录的真实路径
        String relativePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";//相对路径
        File dir=new File(realPath+"upload/");//上传文件夹
        //判断上传文件夹是否存在
        if (!dir.exists()){
            dir.mkdirs();
        }
        System.out.println("文件被上传到（真实路径）"+realPath+"upload/"+filename);
        System.out.println("文件被上传到（相对路径）"+relativePath+"upload/"+filename);
        File targetFile=new File(realPath+"upload/"+filename);//上传文件
        // 判断上传文件是否存在
        if (!targetFile.exists()){
            try {
                targetFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        try {
            file.transferTo(targetFile);//复制源文件内容到上传文件中
            request.setAttribute("sucmsg","上传成功！");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
        return "singleUpload";
    }
}
