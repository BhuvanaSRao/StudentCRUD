import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class DeleteStudentServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        int rollNo = Integer.parseInt(request.getParameter("rollNo"));

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            Connection con = DBConnection.getConnection();
            String query = "Delete from students WHERE roll_no=?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, rollNo);
           

            int result = stmt.executeUpdate();

            if (result > 0) {
                response.sendRedirect("ViewStudentsServlet");
            } else {
                out.println("<h2>Failed to Delete student.</h2>");
            }

        } catch (Exception e) {
            e.printStackTrace();
            out.println("<h2>Error: " + e.getMessage() + "</h2>");
        }
    }
}
