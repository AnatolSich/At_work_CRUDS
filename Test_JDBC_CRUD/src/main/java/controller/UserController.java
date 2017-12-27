package controller;

import dao.UserDao;
import model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static util.ActionOperationEnum.*;

public class UserController extends HttpServlet {
    private static final String INSERT_OR_EDIT = "";
    private static final String USER_LIST = "";
    private UserDao userDao;


    public UserController() {
        this.userDao = new UserDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String actionValue = req.getParameter("action");
        String view = "";

        if (DELETE.toString().equalsIgnoreCase(actionValue)) {
            int userIdValue = Integer.parseInt(req.getParameter("userId"));
            userDao.deleteUser(userIdValue);
            view = USER_LIST;
            req.setAttribute("users", userDao.getAllUsers());
        } else if (CREATE.toString().equalsIgnoreCase(actionValue)) {
            view = INSERT_OR_EDIT;
        } else if (EDIT.toString().equalsIgnoreCase(actionValue)) {
            int userIdValue = Integer.parseInt(req.getParameter("userId"));
            view = INSERT_OR_EDIT;
            req.setAttribute("user", userDao.getUserById(userIdValue));
        } else if (LIST.toString().equalsIgnoreCase(actionValue)) {
            view = USER_LIST;
            req.setAttribute("userList", userDao.getAllUsers());
        } else {
            throw new RuntimeException("Invalid action");
        }

        RequestDispatcher requestDispatcher = req.getRequestDispatcher(view);
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
