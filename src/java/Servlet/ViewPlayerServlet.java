package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import dao.PlayerDao;
import model.Player;


public class ViewPlayerServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.print("<html><head>");
        out.print("<link rel='stylesheet' href='style.css'>");
        out.print("</head><body>");

        //  Get page number
        String spageid = request.getParameter("page");

        if(spageid == null){
            spageid = "1"; // default page
        }

        int pageid = Integer.parseInt(spageid);
        int total = 5;

        if(pageid != 1){
            pageid = pageid - 1;
            pageid = pageid * total + 1;
        }

        //  Fetch records
        List<Player> list = PlayerDao.getRecords(pageid, total);

        //  Heading
        out.print("<h1>IPL Players - Page "+spageid+"</h1>");
        out.print("<a href='index.html'>Add New Player</a><br><br>");

        //  Table
        out.print("<table border='1' width='100%'>");
        out.print("<tr><th>Id</th><th>Name</th><th>Role</th><th>Country</th><th>Team</th><th>Edit</th><th>Delete</th></tr>");

        for(Player p : list){
            out.print("<tr>");

            out.print("<td>"+p.getId()+"</td>");
            out.print("<td>"+p.getName()+"</td>");
            out.print("<td>"+p.getRole()+"</td>");
            out.print("<td>"+p.getCountry()+"</td>");
            out.print("<td>"+p.getTeam()+"</td>");

            
            out.print("<td><a href='EditPlayerServlet?id="+p.getId()+"'>Edit</a></td>");
            out.print("<td><a href='DeletePlayerServlet?id="+p.getId()+"' onclick=\"return confirm('Are you sure?')\">Delete</a></td>");

            out.print("</tr>");
        }

        out.print("</table>");

        //  Pagination links
        out.print("<br>");

        out.print("<a href='ViewPlayerServlet?page=1'>1</a> ");
        out.print("<a href='ViewPlayerServlet?page=2'>2</a> ");
        out.print("<a href='ViewPlayerServlet?page=3'>3</a> ");
        out.print("</body></html>");

        out.close();
    }
}