package com.vladimir1506.rest.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vladimir1506.rest.MyFile;
import com.vladimir1506.rest.FileImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetFileServlet extends HttpServlet {
    private List<MyFile> files;
    private FileImpl fileImpl;

    @Override
    public void init() throws ServletException {
        fileImpl = new FileImpl();

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        MyFile file = fileImpl.getById(id);

        String fileJSON = new ObjectMapper().writeValueAsString(file);
        resp.getWriter().write(fileJSON);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
