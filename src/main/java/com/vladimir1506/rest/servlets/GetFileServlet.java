package com.vladimir1506.rest.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vladimir1506.rest.controller.FileController;
import com.vladimir1506.rest.model.MyFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetFileServlet extends HttpServlet {
    private FileController fileController;

    @Override
    public void init(){
        fileController = new FileController();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        MyFile file = fileController.getFileById(id);
        String fileJSON = new ObjectMapper().writeValueAsString(file);
        resp.getWriter().write(fileJSON);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
