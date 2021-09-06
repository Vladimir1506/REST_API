package com.vladimir1506.rest.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vladimir1506.rest.FileImpl;
import com.vladimir1506.rest.MyFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class DeleteFileServlet extends HttpServlet {
    FileImpl fileImpl;
    MyFile myFile;
    List<MyFile> files;

    @Override
    public void init() throws ServletException {
        fileImpl = new FileImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        files = fileImpl.getAll();
        String filesJSON = new ObjectMapper().writeValueAsString(files);
        resp.getWriter().write(filesJSON);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        MyFile file = fileImpl.getById(id);
        fileImpl.delete(id);
        String path = file.getPath();
        if (path != null) {
            Files.deleteIfExists(Paths.get(path));
        }
        doGet(req, resp);
    }
}
