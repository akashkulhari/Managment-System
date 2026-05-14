package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import model.Player;
import dao.PlayerDao;


public class EditPlayerServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // HTML + CSS
        out.print("<html><head>");
        out.print("<link rel='stylesheet' href='style.css'>");
        out.print("</head><body>");

        out.println("<h1>Update Player</h1>");

        String sid = request.getParameter("id");
        int id = Integer.parseInt(sid);

        // Fetch player
        Player p = PlayerDao.getPlayerById(id);

        out.print("<form action='EditPlayerServlet2' method='post'>");
        out.print("<table>");

        out.print("<tr><td></td><td><input type='hidden' name='id' value='"+p.getId()+"'/></td></tr>");

        out.print("<tr><td>Name:</td><td>");
        out.print("<input type='text' name='name' value='"+p.getName()+"' required/>");
        out.print("</td></tr>");

        out.print("<tr><td>Role:</td><td>");
        out.print("<select name='role' required>");

        out.print("<option value='' disabled>Select Role</option>");

        out.print("<option "+(p.getRole().equals("Batsman")?"selected":"")+">Batsman</option>");
        out.print("<option "+(p.getRole().equals("Bowler")?"selected":"")+">Bowler</option>");
        out.print("<option "+(p.getRole().equals("All-rounder")?"selected":"")+">All-rounder</option>");
        out.print("<option "+(p.getRole().equals("Wicketkeeper")?"selected":"")+">Wicketkeeper</option>");

        out.print("</select>");
        out.print("</td></tr>");

        out.print("<tr><td>Country:</td><td>");
        out.print("<select name='country' required>");

        out.print("<option value='' disabled>Select Country</option>");

        out.print("<option "+(p.getCountry().equals("India")?"selected":"")+">India</option>");
        out.print("<option "+(p.getCountry().equals("Australia")?"selected":"")+">Australia</option>");
        out.print("<option "+(p.getCountry().equals("England")?"selected":"")+">England</option>");
        out.print("<option "+(p.getCountry().equals("South Africa")?"selected":"")+">South Africa</option>");
        out.print("<option "+(p.getCountry().equals("Other")?"selected":"")+">Other</option>");

        out.print("</select>");
        out.print("</td></tr>");

        out.print("<tr><td>Team:</td><td>");
        out.print("<select name='team' required>");

        out.print("<option value='' disabled>Select Team</option>");

        out.print("<option "+(p.getTeam().equals("RCB")?"selected":"")+">RCB</option>");
        out.print("<option "+(p.getTeam().equals("CSK")?"selected":"")+">CSK</option>");
        out.print("<option "+(p.getTeam().equals("MI")?"selected":"")+">MI</option>");
        out.print("<option "+(p.getTeam().equals("KKR")?"selected":"")+">KKR</option>");
        out.print("<option "+(p.getTeam().equals("RR")?"selected":"")+">RR</option>");
        out.print("<option "+(p.getTeam().equals("GT")?"selected":"")+">GT</option>");
        out.print("<option "+(p.getTeam().equals("LSG")?"selected":"")+">LSG</option>");
        out.print("<option "+(p.getTeam().equals("SRH")?"selected":"")+">SRH</option>");
        out.print("<option "+(p.getTeam().equals("DC")?"selected":"")+">DC</option>");
        out.print("<option "+(p.getTeam().equals("PBKS")?"selected":"")+">PBKS</option>");

        out.print("</select>");
        out.print("</td></tr>");

        //Submit
        out.print("<tr><td colspan='2'>");
        out.print("<input type='submit' value='Edit & Save'/>");
        out.print("</td></tr>");

        out.print("</table>");
        out.print("</form>");

        out.print("</body></html>");

        out.close();
    }
}