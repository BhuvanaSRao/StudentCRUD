import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class AddStudentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Set content type and get writer early
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            // Read form values
            int rollNo = Integer.parseInt(request.getParameter("rollNo"));
            String name = request.getParameter("name");
            String className = request.getParameter("class");
            int mark1 = Integer.parseInt(request.getParameter("mark1"));
            int mark2 = Integer.parseInt(request.getParameter("mark2"));
            int mark3 = Integer.parseInt(request.getParameter("mark3"));

            // Connect to DB and prepare insert statement
            Connection con = DBConnection.getConnection();
            String query = "INSERT INTO students (roll_no, name, class, mark1, mark2, mark3) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(query);

            stmt.setInt(1, rollNo);
            stmt.setString(2, name);
            stmt.setString(3, className);
            stmt.setInt(4, mark1);
            stmt.setInt(5, mark2);
            stmt.setInt(6, mark3);

            int result = stmt.executeUpdate();
            int totalMarks;
            float percentage;

            // Show success/failure message in browser
            out.println("<html><body style='font-family:Arial;'>");

            if (result > 0) {
                response.sendRedirect("ViewStudentsServlet");
                out.println("<h2 style='color:green;'>Student Added Successfully!</h2>");
                out.println("<p><strong>Roll No:</strong> " + rollNo + "</p>");
                out.println("<p><strong>Name:</strong> " + name + "</p>");
                out.println("<p><strong>Class:</strong> " + className + "</p>");
                out.println("<p><strong>Subject1 Mark:</strong> " + mark1 + "</p>");
                out.println("<p><strong>Subject2 Mark:</strong> " + mark2 + "</p>");
                out.println("<p><strong>Subject3 Mark:</strong> " + mark3 + "</p>");
                totalMarks = mark1 + mark2 + mark3;
                out.println("<p><strong>Total Marks:</strong> " + totalMarks + "</p>");
                percentage = (totalMarks / 300.0f) * 100; 
                out.println("<p><strong>Percentage:</strong> " + percentage + "</p>");
            } else {
                out.println("<h2 style='color:red;'>Failed to add student.</h2>");
            }

            out.println("<br><a href='index.html'>Back to Form</a>");
            out.println("</body></html>");

        } catch (Exception e) {
            // Show error to browser (optional)
            out.println("<h2 style='color:red;'>Error occurred!</h2>");
            out.println("<pre>" + e.getMessage() + "</pre>");
            e.printStackTrace();
        }
    }
}
