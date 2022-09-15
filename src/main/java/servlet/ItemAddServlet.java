package servlet;

import manager.CategoryManager;
import manager.ItemManager;
import manager.UserManager;
import model.Category;
import model.Item;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/items/add")
@MultipartConfig(

        fileSizeThreshold = 1024 * 1024 * 10,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 100

)
public class ItemAddServlet extends HttpServlet {

    private static final String IMAGE_PATH = "C:\\Users\\DELL\\IdeaProjects\\myItems.am\\img\\";
    private ItemManager itemManager = new ItemManager();
    private CategoryManager categoryManager = new CategoryManager();
    private UserManager userManager = new UserManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Category> categories = categoryManager.getAllCategory();
        req.setAttribute("categories", categories);
        List<User> users = userManager.getAll();
        req.setAttribute("users", users);
        req.getRequestDispatcher("/WEB-INF/addItems.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String title = req.getParameter("title");
        double price = Double.parseDouble(req.getParameter("price"));
        User user = (User) req.getSession().getAttribute("user");
        int categoryId = Integer.parseInt(req.getParameter("categoryId"));
        Part picUrl = req.getPart("picUrl");
        String fileName = null;
        if (picUrl != null && !"".equals(picUrl.getSubmittedFileName())) {
            long nanoTime = System.nanoTime();
            fileName = nanoTime + "_" + picUrl.getSubmittedFileName();
            File directory = new File(IMAGE_PATH);
            if (! directory.exists()){
                directory.mkdir();
            }
            picUrl.write(IMAGE_PATH + fileName);
        }
        Item item = Item.builder()
                .title(title)
                .price(price)
                .picUrl(fileName)
                .user(user)
                .category(categoryManager.getById(categoryId))
                .build();
        itemManager.add(item);
        resp.sendRedirect("/myItems");


    }

}

