package controller.admin;

import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import service.CategoryService;
import service.CategoryServiceImpl;

@WebServlet("/admin/category/list")
public class CategoryListController extends HttpServlet {
    private final CategoryService service = new CategoryServiceImpl();

    @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("items", service.findAll());
        req.getRequestDispatcher("/views/admin/category-list.jsp").forward(req, resp);
    }
}
