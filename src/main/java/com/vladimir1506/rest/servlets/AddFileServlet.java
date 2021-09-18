package com.vladimir1506.rest.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vladimir1506.rest.controller.FileController;
import com.vladimir1506.rest.model.MyFile;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class AddFileServlet extends HttpServlet {
    static final int fileMaxSize = 100 * 1024;
    static final int memMaxSize = 100 * 1024;
    private final String filePath = "src/main/resources/uploads/";
    private FileController fileController;
    List<MyFile> files;

    @Override
    public void init() {
        fileController = new FileController();
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) {
        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        diskFileItemFactory.setRepository(new File(filePath));
        diskFileItemFactory.setSizeThreshold(memMaxSize);

        ServletFileUpload upload = new ServletFileUpload(diskFileItemFactory);
        upload.setSizeMax(fileMaxSize);

        try {
            List<FileItem> fileItems = upload.parseRequest(req);

            for (FileItem fileItem : fileItems) {
                if (!fileItem.isFormField()) {
                    String fileName = fileItem.getName();
                    Long fileSize = fileItem.getSize();
                    File file = new File(filePath, fileName);
                    fileItem.write(file);
                    MyFile myFile = new MyFile(fileName, fileSize, new Date(), file.getPath());
                    fileController.saveFile(myFile);
                    doGet(req, resp);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        files = fileController.getAllFiles();
        String filesJSON = new ObjectMapper().writeValueAsString(files);
        resp.getWriter().write(filesJSON);
    }
}
