package controller.admin;

import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import service.CategoryService;
import service.CategoryServiceImpl;

@WebServlet("/admin/category/delete")
public class CategoryDeleteController extends HttpServlet {
    private final CategoryService service = new CategoryServiceImpl();

    @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        service.delete(id);
        resp.sendRedirect(req.getContextPath()+"/admin/category/list");
    }
}
