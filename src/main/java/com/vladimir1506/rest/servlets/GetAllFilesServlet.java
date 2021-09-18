package com.vladimir1506.rest.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vladimir1506.rest.controller.FileController;
import com.vladimir1506.rest.model.MyFile;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetAllFilesServlet extends HttpServlet {
    private FileController fileController;

    @Override
    public void init() {
        fileController = new FileController();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<MyFile> files = fileController.getAllFiles();
        String filesJSON = new ObjectMapper().writeValueAsString(files);
        resp.getWriter().write(filesJSON);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
    }
}
