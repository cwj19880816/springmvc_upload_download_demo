package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class DownloadController {
    @RequestMapping("/download")
    public String downloadFile(@RequestParam("fileName") String fileName, HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=utf-8");//响应的文件类型和文件编码
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String path = request.getSession().getServletContext().getRealPath("/") + "upload/";//上传文件夹
        String downloadFilePath = path + fileName;
        System.out.println("要下载的文件为：" + downloadFilePath);
        /*BufferedInputStream inputStream = null;
        BufferedOutputStream outputStream = null;*/
        File file = new File(downloadFilePath);
        long fileLength = file.length();
        Path f = Paths.get(path, fileName);
        //设置响应头文件内容、文件类型、弹出下载对话框、文件大小
        try {
            if (Files.exists(f)) {
                response.setContentType("application/x-msdownload");
                response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
                response.setHeader("Content-Length", String.valueOf(fileLength));
            /*inputStream=new BufferedInputStream(new FileInputStream(downloadFilePath));//将文件路径放入文件输入流，再将文件输入流放入输入流缓存
            outputStream=new BufferedOutputStream(response.getOutputStream());//从响应中获取输出流，再将输出流放入输出流缓存
            byte[] bytes=new byte[2048];//字节数组用于缓存
            int length=0;
            while ((length=inputStream.read(bytes,0,bytes.length))!=-1){ //将文件里的内容以输入流读到字节数组中
                outputStream.write(bytes,0,length); //将字节数组中的数据写入到输出流中
            }*/
                Files.copy(f, response.getOutputStream());
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } /*finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }*/
        return null;
    }
}
