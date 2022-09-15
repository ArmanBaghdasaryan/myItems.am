package servlet;


import manager.CategoryManager;
import manager.ItemManager;
import model.Category;
import model.Item;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/categories")
public class CategoriesServlet extends HttpServlet {
    private CategoryManager categoryManager = new CategoryManager();
    private ItemManager itemManager = new ItemManager();

    @Override  protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("categoryId");
        List<Item> items;
        if (id == null || id.equals("")) {
            items = itemManager.getTwentyItems();
        } else {
            int categoryId = Integer.parseInt(id);
            items = itemManager.getItemsByCategoryId(categoryId);
        }
        req.setAttribute("items", items);
        req.setAttribute("categories", categoryManager.getAllCategory());
        req.getRequestDispatcher("/WEB-INF/categories.jsp").forward(req, resp);
    }
}
