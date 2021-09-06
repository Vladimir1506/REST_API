package com.vladimir1506.rest.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vladimir1506.rest.FileImpl;
import com.vladimir1506.rest.MyFile;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class AddFileServlet extends HttpServlet {
    static final int fileMaxSize = 100 * 1024;
    static final int memMaxSize = 100 * 1024;
    private String filePath = "src/main/resources/uploads/";
    private File file;
    FileImpl fileImpl = new FileImpl();
    List<MyFile> files;

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter writer = resp.getWriter();
        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        diskFileItemFactory.setRepository(new File(filePath));
        diskFileItemFactory.setSizeThreshold(memMaxSize);

        ServletFileUpload upload = new ServletFileUpload(diskFileItemFactory);
        upload.setSizeMax(fileMaxSize);

        try {
            List fileItems = upload.parseRequest(req);
            Iterator iterator = fileItems.iterator();

            while (iterator.hasNext()) {
                FileItem fileItem = (FileItem) iterator.next();
                if (!fileItem.isFormField()) {
                    String fileName = fileItem.getName();
                    Long fileSize = fileItem.getSize();
                    file = new File(filePath, fileName);
                    fileItem.write(file);
                    MyFile myFile = new MyFile(fileName, fileSize, new Date(),file.getPath());
                    fileImpl.save(myFile);
                    doGet(req, resp);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        files = fileImpl.getAll();
        String filesJSON = new ObjectMapper().writeValueAsString(files);
        resp.getWriter().write(filesJSON);
    }
}
