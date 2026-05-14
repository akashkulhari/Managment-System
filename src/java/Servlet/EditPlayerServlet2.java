
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Player;
import dao.PlayerDao;
import javax.servlet.annotation.WebServlet;



public class EditPlayerServlet2 extends HttpServlet {
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        
        // Get data from form
        String sid = request.getParameter("id");
        int id = Integer.parseInt(sid);

        String name = request.getParameter("name");
        String role = request.getParameter("role");
        String country = request.getParameter("country");
        String team = request.getParameter("team");

        // Create object
        Player p = new Player();
        p.setId(id);
        p.setName(name);
        p.setRole(role);
        p.setCountry(country);
        p.setTeam(team);

        // Update DB
        int status = PlayerDao.update(p);

        if(status > 0){
            response.sendRedirect("ViewPlayerServlet");  // go back to list
        } else {
            out.println("Error! Unable to update player");
        }

        out.close();
    }
}
   
