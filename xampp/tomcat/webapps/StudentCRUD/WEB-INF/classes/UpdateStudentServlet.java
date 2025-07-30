import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class UpdateStudentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        int rollNo = Integer.parseInt(request.getParameter("rollNo"));
        String name = request.getParameter("name");
        String className = request.getParameter("class");
        int mark1 = Integer.parseInt(request.getParameter("mark1"));
        int mark2 = Integer.parseInt(request.getParameter("mark2"));
        int mark3 = Integer.parseInt(request.getParameter("mark3"));

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            Connection con = DBConnection.getConnection();
            String query = "UPDATE students SET name=?, class=?, mark1=?, mark2=?, mark3=? WHERE roll_no=?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, name);
            stmt.setString(2, className);
            stmt.setInt(3, mark1);
            stmt.setInt(4, mark2);
            stmt.setInt(5, mark3);
            stmt.setInt(6, rollNo);

            int result = stmt.executeUpdate();

            if (result > 0) {
                response.sendRedirect("ViewStudentsServlet");
            } else {
                out.println("<h2>Failed to update student.</h2>");
            }

        } catch (Exception e) {
            e.printStackTrace();
            out.println("<h2>Error: " + e.getMessage() + "</h2>");
        }
    }
}
