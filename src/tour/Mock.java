package tour;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import static org.mockito.Mockito.*;


@WebServlet("/Mock")
public class Mock extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	      final String DB_URL="jdbc:mysql://localhost/coursfan";

	      
	      final String USER = "root";
	      final String PASS = "cvbhyjd11";
		
		String author=new String("Ivanov");
		String text=new String ("My fanfiction");
		
		List <String> texts = new ArrayList <String>();
		texts.add(author);
		texts.add(text);
		
		 response.setContentType("text/html");
	      PrintWriter out = response.getWriter();
	      String title = "Database Result";
	      
	      String docType =
	         "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
	      
	      out.println(docType +
	         "<html>\n" +
	         "<head><title>" + title + "</title></head>\n" +
	         "<body bgcolor = \"#f0f0f0\">\n" +
	         "<h1 align = \"center\">" + title + "</h1>\n");
	      out.println(texts);
	      out.println("</body></html>");
	      
	      Connection con=mock(Connection.class);
	      
	      
	     try {
			when (con.createStatement()).thenReturn(null);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
