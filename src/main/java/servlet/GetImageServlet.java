package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet(urlPatterns = "/image")
public class GetImageServlet extends HttpServlet {
    private static final String IMAGE_PATH = "C:\\Users\\DELL\\IdeaProjects\\myItems.am\\img\\";

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getParameter("path");
        if (path == null || path.length() == 0) {
            resp.sendRedirect("/main");
        }
        File file = new File(IMAGE_PATH + File.separator + path);
        if (!file.exists()) {
            resp.sendRedirect("/main");
        } else {
            resp.setContentType("image/jpeg");
            resp.setHeader("Content-disposition", "attachment; filename=" + path);

            try (InputStream in = new FileInputStream(file);
                 OutputStream out = resp.getOutputStream()) {
                byte[] buffer = new byte[1048];
                int numBytesRead;
                while ((numBytesRead = in.read(buffer)) > 0) {
                    out.write(buffer, 0, numBytesRead);
                }
            }
        }

    }

//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String profilePic = "picUrl";
//        String filePath = IMAGE_PATH + profilePic;
//        File imageFile = new File(filePath);
//        if (imageFile.exists()) {
//            try (FileInputStream inputStream = new FileInputStream(imageFile)) {
//                resp.setContentType("image/jpeg");
//                resp.setContentLength((int) imageFile.length());
//
//                OutputStream outputStream = resp.getOutputStream();
//                byte[] buffer = new byte[4096];
//                int bytesRead = -1;
//
//                while ((bytesRead = inputStream.read(buffer)) != -1) {
//                    outputStream.write(buffer, 0, bytesRead);
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }

}