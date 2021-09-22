package com.vladimir1506.rest.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vladimir1506.rest.controller.UserController;
import com.vladimir1506.rest.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UserServlet extends HttpServlet {
    private List<User> users;
    private UserController userController;

    @Override
    public void init() {
        userController = new UserController();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String userId = req.getHeader("user_id");
        if (userId == null) {
            users = userController.getAllUsers();
            String usersJSON = new ObjectMapper().writeValueAsString(users);
            resp.getWriter().write(usersJSON);
        } else {
            Long id = Long.parseLong(userId);
            User user = userController.getUserById(id);
            String userJSON = new ObjectMapper().writeValueAsString(user);
            resp.getWriter().write(userJSON);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String name = req.getHeader("user_name");
        User user = new User(name);
        userController.saveUser(user);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp)  {
        Long id = Long.parseLong(req.getHeader("user_id"));
        User user = userController.getUserById(id);
        userController.deleteUser(id);
    }


}
