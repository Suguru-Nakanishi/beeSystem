package tool;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.action")
public class FrontController extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Action action = (Action) Class.forName(request.getServletPath().substring(1).replace(".a", "A").replace('/', '.')).newInstance();
			request.getRequestDispatcher("/WEB-INF/jsp/" + action.execute(request, response) + ".jsp").forward(request, response);
		} catch (Exception e) {e.printStackTrace();}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {doPost(request, response);}
}