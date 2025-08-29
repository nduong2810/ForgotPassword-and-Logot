package controller.admin;

import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.Category;
import service.CategoryService;
import service.CategoryServiceImpl;

@WebServlet("/admin/category/add")
public class CategoryAddController extends HttpServlet {
    private final CategoryService service = new CategoryServiceImpl();

    @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/views/admin/category-add.jsp").forward(req, resp);
    }

    @Override protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Category c = new Category();
        c.setName(req.getParameter("name"));
        c.setIcon(req.getParameter("icon"));
        boolean ok = service.create(c);
        if (ok) resp.sendRedirect(req.getContextPath()+"/admin/category/list");
        else { req.setAttribute("error","Không thể thêm"); doGet(req, resp); }
    }
}
