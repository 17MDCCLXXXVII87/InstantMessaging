package filter;

import java.io.IOException;

import bean.User;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.AppUtils;
import utils.SecurityUtils;

@WebFilter("/*")
public class SecurityFilter implements Filter{
	public SecurityFilter() {
	}
	
	@Override
	public void destroy() {
	}
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException{		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		
		String servletPath = request.getServletPath();
		
		User loginedUser = AppUtils.getLoginedUser(request.getSession());
		
		if(servletPath.equals("/login")) {
			chain.doFilter(request, response);
			return;
		}
		
		if(SecurityUtils.isSecurityPage(request)) {
			if(loginedUser == null) {
				String requestUri = request.getRequestURI();
				int redirectId = AppUtils.storeRedirectAfterLoginUrl(request.getSession(), requestUri);
				
				response.sendRedirect(request.getContextPath() + "/login?redirectId=" + redirectId);
				return;
			}
		}
		chain.doFilter(request, response);
	}
}
