package servlet;

import manager.ItemManager;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/myItems/remove")
public class RemoveItemServlet extends HttpServlet {
    ItemManager itemManager = new ItemManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("itemId"));
        itemManager.deleteItemById(id);
        req.getRequestDispatcher("/WEB-INF/myItems.jsp");
        resp.sendRedirect("/myItems");
    }
}
