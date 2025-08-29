package filter;

import java.io.IOException;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;

import model.User;

@WebFilter(urlPatterns = {"/admin/*"})
public class AdminFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req  = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        HttpSession session = req.getSession(false);
        User account = (session == null) ? null : (User) session.getAttribute("account");

        if (account == null) {
            // Chưa đăng nhập
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        int role = account.getRoleid(); // 1=Admin, 2=Manager, 3=User
        if (role == 1 || role == 2) {
            chain.doFilter(request, response); // đủ quyền
        } else {
            req.setAttribute("reason", "Bạn không có quyền truy cập khu vực quản trị.");
            req.getRequestDispatcher("/views/403.jsp").forward(req, resp);
        }
    }
}
