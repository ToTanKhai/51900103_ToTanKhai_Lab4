package web;

import java.io.IOException;

import database.LoginDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.LoginBean;

@WebServlet(urlPatterns = {"/login", "/hello"})
public class LoginServlet extends HttpServlet {
	private LoginDAO loginDAO;

    public void init() {
        loginDAO = new LoginDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        LoginBean loginBean = new LoginBean();
        loginBean.setUsername(username);
        loginBean.setPassword(password);

        try {
            if (loginDAO.validate(loginBean)) {
                response.sendRedirect("loginsuccess.html");
            } else {
//                HttpSession session = request.getSession();
                response.sendRedirect("login.html");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

