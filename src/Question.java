import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;


public class Question extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException
    {
		//Random rand =new Random();
		//int n=rand.nextInt(3)+1;
		
		String URL="jdbc:oracle:thin:@localhost:1521:xe";
		String USER ="Jeevan";
		String PASS ="jeevan";

		String ques=request.getParameter("ques");
		String op1=request.getParameter("op1");
		String op2=request.getParameter("op2");
		String op3=request.getParameter("op3");
		String op4=request.getParameter("op4");
		String ans=request.getParameter("ans");
		
		
		response.setContentType("text/html");
		
		PrintWriter out=response.getWriter();
		
		out.println("<html>");
		out.println("<head><title>C-Star</title></head>");
        out.println("<body>");
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//Driver myDriver = new oracle.jdbc.driver.OracleDriver();
			//DriverManager.registerDriver(myDriver);

			Connection con =DriverManager.getConnection(URL,USER,PASS);
			
			out.println("<h1>Connected TO Database</h1>");
			
			//out.println("<h3>Name: " + area + "</h3>");
			
			//Statement stmt=con.createStatement();
			//stmt.executeUpdate("insert into registration (name,phno,clgname,regno) values (' "+stud_name+" ',' "+phno+" ',' "+clg_name+" ',' "+reg_no+" ')");
			
			PreparedStatement pstmt = con.prepareStatement("INSERT INTO ques_ans (ques,op1,op2,op3,op4,ans) values(?,?,?,?,?,?)");
			
			pstmt.setString(1,ques);
			pstmt.setString(2,op1);
			pstmt.setString(3,op2);
			pstmt.setString(4,op3);
			pstmt.setString(5,op4);
			pstmt.setString(6,ans);
			
			pstmt.executeUpdate();
			
			out.println("<h2>record Inserted</h2>");
			out.println("<h4><a href="../login.html">click to add more questions</a></h4>")
			out.println("</body>");
			out.println("</html>");
        
			
			pstmt.close();
			con.close();
				
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