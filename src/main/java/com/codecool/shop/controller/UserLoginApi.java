package com.codecool.shop.controller;

import com.codecool.shop.dao.implementation.UserDaoMem;
import com.codecool.shop.model.User;
import com.google.gson.Gson;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = {"/login"})
public class UserLoginApi extends HttpServlet {

    private UserDaoMem userDaoMem = UserDaoMem.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String dataJson = req.getReader().lines().collect(Collectors.joining());
        Gson gson = new Gson();
        DataPropertiesLogin data = gson.fromJson(dataJson, DataPropertiesLogin.class);
        User user = userDaoMem.find(data.loginName);

        PasswordLegit passwordLegit =  new PasswordLegit();
        if (user != null) {
            System.out.println(user.getPassword());
            passwordLegit.passwordLegit = BCrypt.checkpw(data.loginPassword, user.getPassword());
        }
        passwordLegit.name = data.loginName;

        String responseJson = gson.toJson(passwordLegit);
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        out.print(responseJson);
        out.flush();
    }

    public class DataPropertiesLogin {
        public String loginName;
        public String loginPassword;
    }

    public class PasswordLegit {
        public Boolean passwordLegit;
        public String name;
    }
}
