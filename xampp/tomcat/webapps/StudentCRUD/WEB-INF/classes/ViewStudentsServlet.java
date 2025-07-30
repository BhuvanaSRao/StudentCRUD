import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class ViewStudentsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM students");

            out.println("<html><body style='font-family: Arial;'>");
            out.println("<h2 style='text-align:center;'>Student List</h2>");
            out.println("<table border='1' cellpadding='10' style='margin:auto;'>");
            out.println("<tr><th>Roll No</th><th>Name</th><th>Class</th><th>Mark1</th><th>Mark2</th><th>Mark3</th><th>Total</th><th>%</th><th>Actions</th></tr>");

            while (rs.next()) {
                int rollNo = rs.getInt("roll_no");
                String name = rs.getString("name");
                String className = rs.getString("class");
                int m1 = rs.getInt("mark1");
                int m2 = rs.getInt("mark2");
                int m3 = rs.getInt("mark3");
                int total = m1 + m2 + m3;
                float percent = (total / 300.0f) * 100;

                out.println("<tr>");
                out.println("<td>" + rollNo + "</td>");
                out.println("<td>" + name + "</td>");
                out.println("<td>" + className + "</td>");
                out.println("<td>" + m1 + "</td>");
                out.println("<td>" + m2 + "</td>");
                out.println("<td>" + m3 + "</td>");
                out.println("<td>" + total + "</td>");
                out.println("<td>" + String.format("%.2f", percent) + "%</td>");
                out.println("<td>");
                out.println("<a href='EditStudentServlet?rollNo=" + rollNo + "'>Edit</a> | ");
                out.println("<a href='DeleteStudentServlet?rollNo=" + rollNo + "' onclick='return confirm(\"Are you sure?\")'>Delete</a>");
                // Adding a button for editing the student(When displaying each student record, add a form or hyperlink for editing)
                out.println("<td><a href='EditStudentServlet?rollNo=" + rollNo + "' class='edit-btn'>Edit</a></td>");
                out.println("</td>");
                out.println("</tr>");
            }

            out.println("</table>");
            out.println("<br><div style='text-align:center;'><a href='index.HTML'>Add New Student</a></div>");
            out.println("</body></html>");

            con.close();
        } catch (Exception e) {
            out.println("<p style='color:red;'>Error: " + e.getMessage() + "</p>");
            e.printStackTrace();
        }
    }
}
