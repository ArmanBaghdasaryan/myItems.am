package servlet;

import manager.CategoryManager;
import manager.ItemManager;
import model.Item;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/main")
public class MainPageServlet extends HttpServlet {


    private CategoryManager categoryManager = new CategoryManager();

    private ItemManager itemManager = new ItemManager();


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String catId = req.getParameter("categoryId");
        List<Item> items;
        if (catId == null || catId.equals("")) {
            items = itemManager.getTwentyItems();
        } else {
            int categoryId = Integer.parseInt(catId);
            items = itemManager.getItemsByCategoryId(categoryId);
        }
        req.setAttribute("items", items);
        req.setAttribute("categories", categoryManager.getAllCategory());
        req.getRequestDispatcher("/WEB-INF/mainPage.jsp").forward(req, resp);
    }
}
