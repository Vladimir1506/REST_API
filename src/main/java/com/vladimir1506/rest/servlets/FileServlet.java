package com.vladimir1506.rest.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vladimir1506.rest.controller.FileController;
import com.vladimir1506.rest.controller.UserController;
import com.vladimir1506.rest.model.MyFile;
import com.vladimir1506.rest.model.User;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FileServlet extends HttpServlet {
    private List<MyFile> allFiles;

    private FileController fileController;
    static final int fileMaxSize = 100 * 1024;
    static final int memMaxSize = 100 * 1024;
    private final String filePath = "src/main/resources/uploads/";

    @Override
    public void init() {
        fileController = new FileController();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<MyFile> files=new ArrayList<>();
        String filesJSON;
        String id = req.getHeader("user_id");
        allFiles = fileController.getAllFiles();

        if (id != null) {
            Long userId = Long.parseLong(id);
            allFiles.stream().filter(file -> file.getUser().getId().equals(userId)).forEach(files::add);
            filesJSON = new ObjectMapper().writeValueAsString(files);
        } else {
            filesJSON = new ObjectMapper().writeValueAsString(allFiles);
        }
        resp.getWriter().write(filesJSON);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        UserController userController = new UserController();
        Long userId = Long.parseLong(req.getHeader("user_id"));
        List<User> users = userController.getAllUsers();
        if (users.isEmpty()) {
            resp.getWriter().write("Ни одного пользователя не создано!");
            return;
        }
        User user = userController.getUserById(userId);
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
                    myFile.setUser(user);
                    fileController.saveFile(myFile);
                    String fileJSON = new ObjectMapper().writeValueAsString(myFile);
                    resp.getWriter().write(fileJSON);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long id = Long.parseLong(req.getHeader("file_id"));
        MyFile file = fileController.getFileById(id);
        fileController.deleteFile(id);
        String path = file.getPath();
        if (path != null) {
            Files.deleteIfExists(Paths.get(path));
        }
    }
}
