package tour;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/SimpleImage")
public class SimpleImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		response.setContentType("image/jpeg");
		ServletOutputStream out = response.getOutputStream();
		ImageIO.write(drawGraphics(), "jpeg", out);
	}
	ArrayList<Integer>listX = new ArrayList<Integer>();
	ArrayList<Integer>listY = new ArrayList<Integer>();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		Connection dbConnection = null;
		Statement statement = null;
		
		String selectTableSQL = "SELECT TEXT1 FROM text";
		ResultSet rs = null;
		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			statement.execute(selectTableSQL);
			rs = statement.executeQuery(selectTableSQL);
			System.out.println("ok!");
			while(rs.next())
			{
				listX.add(rs.getInt("x"));
				listY.add(rs.getInt("y"));	
			
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (dbConnection != null) {
				try {
					dbConnection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		processRequest(request, response);
	}
	
	private BufferedImage drawGraphics() {
		BufferedImage imgBuff = new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = imgBuff.createGraphics();
		g2.setFont(new Font("Serif", Font.ROMAN_BASELINE, 52));
		g2.setColor(Color.GREEN);

		int x1=250, x2=250, y1 = 250, y2 = 250;
		for (int i =0; i<listX.size(); i++){
			x2= 250 + listX.get(i);
			y2= 250 - listY.get(i);
			g2.drawLine(x1, y1, x2, y2);
			x1=x2;
			y1=y2;
			
		}
		g2.setColor(Color.white);
		x1=0;
		y1=250;
		x2=500;
		y2=250;
		g2.drawLine(x1, y1, x2, y2);
		y1=0;
		y2=500;
		x1=x2=250;
		g2.drawLine(x1, y1, x2, y2);
		
		g2.dispose();
		return imgBuff;
	}

	private static Connection getDBConnection() {
		Connection dbConnection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost/graph";
			String name = "root";
			String password = "cvbhyjd11";
			try {
				dbConnection = DriverManager.getConnection(url, name, password);
				return dbConnection;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return dbConnection;
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
