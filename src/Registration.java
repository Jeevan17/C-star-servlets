import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
//import java.util.Random;


public class Registration extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException
    {
		//Random rand =new Random();
		//int n=rand.nextInt(3)+1;
		
		String URL="jdbc:oracle:thin:@localhost:1521:xe";
		String USER ="Jeevan";
		String PASS ="jeevan";

		String stud_name=request.getParameter("student_name");
		String phno=request.getParameter("phno");
		String clg_name=request.getParameter("clg_name");
		String reg_no=request.getParameter("reg_no");
		
		//String sql="insert into registration values("	+ stud_name +","+ phno +","	+ clg_name +","	+ reg_no +")";
		//String sql="insert into registration+values('jeevan','9000583295','cbit','1234')";
		response.setContentType("text/html");
		
		PrintWriter out=response.getWriter();
		
		out.println("<html>");
		out.println("<head><title>Jeevan Gandla</title><head>");
        out.println("<body>");
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//Driver myDriver = new oracle.jdbc.driver.OracleDriver();
			//DriverManager.registerDriver(myDriver);

			Connection con =DriverManager.getConnection(URL,USER,PASS);
			
			out.println("<h1>Connected TO Database</h1>");
			
			out.println("<h3>Name: " + stud_name + "</h3>");
			out.println("<h3>PHno: " + phno + "</h3>");
			out.println("<h3>clg_name: " + clg_name + " </h3>");
			out.println("<h3>reg_no: " + reg_no + "</h3>");
			
			//Statement stmt=con.createStatement();
			//stmt.executeUpdate("insert into registration (name,phno,clgname,regno) values (' "+stud_name+" ',' "+phno+" ',' "+clg_name+" ',' "+reg_no+" ')");
			
			PreparedStatement pstmt = con.prepareStatement("INSERT INTO registration (name,phno,clgname,regno) values(?,?,?,?)");
			
			pstmt.setString(1,stud_name);
			pstmt.setString(2,phno);
			pstmt.setString(3,clg_name);
			pstmt.setString(4,reg_no);
			
			pstmt.executeUpdate();
			
			out.println("<h2>record Inserted</h2>");
			out.println("</body>");
			out.println("</html>");
        
			
			pstmt.close();
			con.close();
			
			//String next="src/set"+n+".jsp";
			response.sendRedirect("src/quest.jsp");
			//out.println("<h2>set1.jsp</h2>");
			
		}
		catch(Exception ex)
		{
			System.out.println("Jeevangandla: "+ ex);
			System.exit(1);
		}
		
    }
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
      doGet(request, response);
   }
}