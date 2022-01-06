package servlet;

import java.io.IOException;

import bean.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.AppUtils;
import utils.DataDAO;

@WebServlet("/login")
public class LoginServlet extends HttpServlet{
	
	public LoginServlet() {
		super();
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		this.getServletContext().getRequestDispatcher("/WEB-INF/views/index.jsp").forward(request, response);
		return;
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		User u = DataDAO.findUser(email, password);
		
		if(u == null) {
			request.setAttribute("errorMessage", "Invalid email or password");
			this.getServletContext().getRequestDispatcher("/WEB-INF/views/index.jsp").forward(request, response);
			return;
		}
		
		AppUtils.storeLoginedUser(request.getSession(), u);
		
		int redirectId = -1;
		try {
			redirectId = Integer.parseInt(request.getParameter("redirectId"));
		}catch (Exception e) {
		}
		String requestUrl = AppUtils.getRedirectAfterLoginUrl(request.getSession(), redirectId);
		if(requestUrl != null) {
			response.sendRedirect(requestUrl);
		}else {
			response.sendRedirect(request.getContextPath() + "/user");
		}
	}
}
