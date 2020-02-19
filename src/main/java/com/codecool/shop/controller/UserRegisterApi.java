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



@WebServlet(urlPatterns = {"/registration"})
public class UserRegisterApi extends HttpServlet {

    private UserDaoMem userDaoMem = UserDaoMem.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String dataJson = req.getReader().lines().collect(Collectors.joining());
        Gson gson = new Gson();
        DataPropertiesRegister data = gson.fromJson(dataJson, DataPropertiesRegister.class);

        RegistrationLegit registrationLegit = new RegistrationLegit();
        if (userDaoMem.find(data.registerName) == null) {
            userDaoMem.add(
                    new User(data.registerName, BCrypt.hashpw(data.registerPassword, BCrypt.gensalt(12))));
            registrationLegit.registrationLegit = true;
        }

        String responseJson = gson.toJson(registrationLegit);
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        out.print(responseJson);
        out.flush();

    }

    public class DataPropertiesRegister {
        public String registerName;
        public String registerPassword;
    }

    public class RegistrationLegit {
        public Boolean registrationLegit = false;
    }




}
