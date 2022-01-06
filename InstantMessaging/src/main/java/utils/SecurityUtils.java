package utils;

import java.util.Set;

import config.SecurityConfig;
import jakarta.servlet.http.HttpServletRequest;

public class SecurityUtils {

	public static boolean isSecurityPage(HttpServletRequest request) {
		String urlPattern = UrlPatternUtils.getUrlPattern(request);
		
		Set<String> urlPatterns = SecurityConfig.getUrlPatterns();
		if(urlPatterns != null && urlPatterns.contains(urlPattern)) {
			return true;
		}
		return false;
	}
}
