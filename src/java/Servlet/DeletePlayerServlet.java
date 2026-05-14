package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import dao.PlayerDao;


public class DeletePlayerServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        String sid = request.getParameter("id");
        int id = Integer.parseInt(sid);

        // 🔹 Delete player
        PlayerDao.delete(id);

        // 🔹 Redirect to view page
        response.sendRedirect("ViewPlayerServlet");
    }
}