package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import model.User;
import service.UserService;
import service.UserServiceImpl;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User user = userService.login(username, password);
        if (user == null) {
            req.setAttribute("error", "Sai tài khoản hoặc mật khẩu");
            req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
            return;
        }

        // Lưu session
        HttpSession session = req.getSession(true);
        session.setAttribute("account", user);

        // Điều hướng theo quyền:
        int role = user.getRoleid(); // 1=Admin, 2=Manager, 3=User
        if (role == 1 || role == 2) {
            resp.sendRedirect(req.getContextPath() + "/admin/category/list");
        } else {
            // user thường
            resp.sendRedirect(req.getContextPath() + "/views/hello.jsp");
        }
    }
}
