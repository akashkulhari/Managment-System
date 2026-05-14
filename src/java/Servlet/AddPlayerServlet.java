package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import model.Player;
import dao.PlayerDao;

@WebServlet("/AddPlayerServlet")
public class AddPlayerServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // 🔹 Get form data
        String name = request.getParameter("name");
        String role = request.getParameter("role");
        String country = request.getParameter("country");
        String team = request.getParameter("team");

        // 🔹 Create object
        Player p = new Player();

        p.setName(name);
        p.setRole(role);
        p.setCountry(country);
        p.setTeam(team);

        // 🔹 Save in DB
        int status = PlayerDao.save(p);

        // 🔹 HTML Start
        out.print("<html>");

        out.print("<head>");
        out.print("<title>Add Player</title>");
        out.print("<link rel='stylesheet' href='style.css'>");
        out.print("</head>");

        out.print("<body>");

        // 🔥 Navbar
        out.print("<div class='navbar'>");

        out.print("<h2>IPL Manager</h2>");

        out.print("<div>");
        out.print("<a href='index.html'>Add Player</a>");
        out.print("<a href='ViewServlet?page=1'>View Players</a>");
        out.print("</div>");

        out.print("</div>");

        // 🔹 Container
        out.print("<div class='container'>");

        out.print("<h1>Result</h1>");

        // 🔥 Success / Failure Message
        if(status > 0){

            out.print("<h2 style='color:green; text-align:center;'>");
            out.print("✅ Player added successfully!");
            out.print("</h2>");

        } else {

            out.print("<h2 style='color:red; text-align:center;'>");
            out.print("❌ Failed to add player");
            out.print("</h2>");
        }

        // 🔹 Buttons
        out.print("<div style='text-align:center; margin-top:20px;'>");

        out.print("<a href='index.html' class='add-btn'>Add Another Player</a> ");

        out.print("<a href='ViewServlet?page=1' class='add-btn'>View Players</a>");

        out.print("</div>");

        out.print("</div>");

        out.print("</body>");
        out.print("</html>");

        out.close();
    }
}