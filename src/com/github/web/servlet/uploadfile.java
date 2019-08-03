package com.github.web.servlet;

import com.alibaba.fastjson.JSON;
import com.github.util.UploadUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/uploadfile")
public class uploadfile extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 数据的接收
        // 文件上传基本操作:
        try {
            // 1.创建一个磁盘文件项工厂对象
            DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
            // 2.创建一个核心解析类
            ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
            // 3.解析request请求，返回的是List集合，List集合中存放的是FileItem对象
            List<FileItem> list = servletFileUpload.parseRequest(request);
            // 定义一个List集合，用于保存兴趣爱好数据:
            List<String> hobbyList = new ArrayList<String>();
            // 4.遍历集合，获得每个FileItem，判断是表单项还是文件上传项
            String url = null;
            for (FileItem fileItem : list) {
                // 判断是表单项还是文件上传项
                if(fileItem.isFormField()){
                    // 普通表单项:
                    // 接收表单项参数的值:
                    String name = fileItem.getFieldName(); // 获得表单项的name属性的值
                    String value = fileItem.getString("UTF-8");// 获得表单项的值
                    System.out.println(name+"    "+value);
                }else{
                    // 文件上传项:
                    // 文件上传功能：
                    // 获得文件上传的名称：
                    String fileName = fileItem.getName();
                    if(fileName !=null && !"".equals(fileName)){
                        // 通过工具类获得唯一文件名:
                        String uuidFileName = UploadUtils.getUUIDFileName(fileName);
                        // 获得文件上传的数据：
                        InputStream is = fileItem.getInputStream();
                        // 获得文件上传的路径:
                        String path = "/home/chenjiangchao/www";
                        // 将输入流对接到输出流就可以了:
                        url = path+"/"+uuidFileName;
                        String src = "http://172.20.151.112:8088/"+uuidFileName;
                        System.out.println(src);
                        OutputStream os = new FileOutputStream(url);
                        int len = 0;
                        byte[] b = new byte[1024];
                        while((len = is.read(b))!=-1){
                            os.write(b, 0, len);
                        }
                        is.close();
                        os.close();
                        JSON.writeJSONString(response.getWriter(), src);
                    }
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
