import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class EditStudentServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        int rollNo = Integer.parseInt(request.getParameter("rollNo"));//This retrieves the roll number from the request parameter
        response.setContentType("text/html");// Set the content type for the response
        PrintWriter out = response.getWriter();

        try {
            Connection con = DBConnection.getConnection();// Establish a connection to the database
            // Prepare a SQL query to fetch the student details based on roll number
            String query = "SELECT * FROM students WHERE roll_no = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, rollNo);//set the roll number in the prepared statement
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                out.println("<html><body>");
                out.println("<h2>Edit Student</h2>");
                out.println("<form action='UpdateStudentServlet' method='post'>");
                out.println("<input type='hidden' name='rollNo' value='" + rollNo + "'>");
                out.println("Name: <input type='text' name='name' value='" + rs.getString("name") + "'><br>");
                out.println("Class: <input type='text' name='class' value='" + rs.getString("class") + "'><br>");
                out.println("Mark 1: <input type='number' name='mark1' value='" + rs.getInt("mark1") + "'><br>");
                out.println("Mark 2: <input type='number' name='mark2' value='" + rs.getInt("mark2") + "'><br>");
                out.println("Mark 3: <input type='number' name='mark3' value='" + rs.getInt("mark3") + "'><br>");
                out.println("<input type='submit' value='Update'>");
                out.println("</form></body></html>");
            } else {
                out.println("Student not found.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            out.println("Error: " + e.getMessage());
        }
    }
}
