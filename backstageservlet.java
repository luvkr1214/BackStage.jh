/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

//import com.sun.jdi.connect.spi.Connection;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import jakarta.servlet.RequestDispatcher;
import java.sql.SQLException;


/**
 *
 * @author HP
 */
@WebServlet(name = "backstageservlet", urlPatterns = {"/backstageservlet"})
public class backstageservlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 1. To set the type of response we'll be sending back
        response.setContentType("text/html;charset=UTF-8");
       
        // 2. To help us write something in response
        PrintWriter out = response.getWriter();
        
        // 3. To make containers to store the fetched data
        String fullname = request.getParameter("fullname");
        String mobile = request.getParameter("mobile");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String gender = request.getParameter("gender");
        String query = request.getParameter("guery");
        
        // 4. To connect the database
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
           Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/backstage", "root", "Baba@1214");
           //out.println("Connected to the database");
           // 5. inserting values using positional parameters.
          PreparedStatement ps = con.prepareStatement("INSERT INTO register (fullname, mobile, email, address, gender, query)VALUES(?,?,?,?,?,?)");
           ps.setString(1, fullname);
           ps.setString(2, mobile);
           ps.setString(3, address);
           ps.setString(4, email);
           ps.setString(5, gender);
           ps.setString(6, query);
           
           int count = ps.executeUpdate();
           if(count>0){
               out.print("<h5 style='color:greeen;'>You've Signed Up Successfully.</h5>");
               RequestDispatcher rd = request.getRequestDispatcher("/index_1.html");
               rd.include(request, response);
               
           }else{
               out.print("<h5 style='color:red;'>You've failed to Sign Up Successfully.</h5>");
               RequestDispatcher rd = request.getRequestDispatcher("/index_1.html");
               rd.include(request, response);
           }  
           
        }catch(ServletException | IOException | ClassNotFoundException | SQLException e){
            out.print("<h5 style='color:greeen;'> Exception Occured:"+e.getMessage()+"</h5>");
            RequestDispatcher rd = request.getRequestDispatcher("/index_1.html");
            rd.include(request, response);
        }
        
    }
}
