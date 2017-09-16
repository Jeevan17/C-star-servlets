<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.servlet.*" %>
<%@ page import="java.servlet.http.*" %>
<%@ page import="java.util.Random" %>

<html>
<head>
	<title>C-Star</title>
	<link rel="stylesheet" href="../style.css">
</head>
<body>
	
	<div class="main-text">
			<!--img src="../images/logo.jpg" width="150px" height="100px"-->
			C-Star
	</div>
	<%
		String URL="jdbc:oracle:thin:@localhost:1521:xe";
		String USER ="Jeevan";
		String PASS ="jeevan";
		ArrayList<Integer> id=new ArrayList<Integer>();
		ArrayList<Integer> answer=new ArrayList<Integer>();
		int i=-1,n,ans=0,key,count=0,inp;
		int[] iid={2,4,1,3};
		
		try{
		HttpSession sess = request.getSession(false);
		i=(Integer)sess.getAttribute("i2");
		ans=(Integer)sess.getAttribute("ans");
		count=(Integer)sess.getAttribute("count");
		out.println(i);
		out.println("Answer: "+ans);
		inp=Integer.parseInt(request.getParameter("que"));
		out.println("Input: "+inp);
		if(ans==inp)
			count++;
		out.println("Count: "+count);
		}
		catch(Exception e){
			out.println("Jeevan: "+ e);
			i=0;
		}
		finally{
			Connection con = null;
			Statement stmt = null;
			ResultSet rs = null;
			try
			{
				Class.forName("oracle.jdbc.driver.OracleDriver");
			
				con =DriverManager.getConnection(URL,USER,PASS);
			
				stmt=con.createStatement();
				
	%>
	<div class="question_content">
		<%
				String act;
				int flag=1;
					
				if(i<iid.length)
				{					
					String sql="Select id,ques,op1,op2,op3,op4,ans from ques_ans where id="+iid[i]+"";
					rs = stmt.executeQuery(sql);
					
					while(rs.next())
					{
		%>				<div class="question">
							<% out.println(rs.getString("ques")); %>
							<% key=rs.getInt("id");
							   id.add(new Integer(key));
							%>
							<div class='opt_content'>
								<form action='quest.jsp' method='post'>
									<input type='radio' name='que' value='1' ><%out.println(rs.getString("op1")); %>
									<input type='radio' name='que' value='2' ><%out.println(rs.getString("op2")+"<br>"); %>
									<input type='radio' name='que' value='3' ><%out.println(rs.getString("op3")); %>
									<input type='radio' name='que' value='4' ><%out.println(rs.getString("op4")); %>
									<center>
										<%if(i==(iid.length)-1){%>
											<input type='submit' value='Submit' class='reg_btn'>
										<%	}else{%>
											<input type='submit' value='Next' class='reg_btn'>
										<%}%>
									</center>
								
							</div>
						</div>
				
				<%
					ans=rs.getInt("ans");
					answer.add(new Integer(ans));
					}
				}
				else{
					out.println("<center>Your Score is: "+count+"</center>");
					i=-1;
					count=0;
					ans=0;
					inp=0;
					//response.sendRedirect("http://localhost:8080/test/src/quest.jsp");
					//sess.invalidate();
				}
				//out.println(id);
				//out.println("<center><a href='validate.jsp' class='reg_btn'>Click Me</a></center>");
						
				stmt.close();
				con.close();
				
				
				HttpSession ses = request.getSession(); 
				//sess.setAttribute("ans", answer);
				//sess.setAttribute("key", id);
				i++;
				ses.setAttribute("i2",i);
				ses.setAttribute("ans",ans);
				ses.setAttribute("count",count);
				//for(int j=0;j<4;j++)
					//out.println("<h1>"+answer.get(j)+"<u>"+id.get(j)+"</u></h1>");
			}
			catch(Exception ex)
			{
				out.println("Jeevangandla: "+ ex);
			}
		}
		%>
	</div>
</body>
</html>