package config;

import java.util.HashSet;
import java.util.Set;

public class SecurityConfig {

	private static final Set<String> setConfig = new HashSet<String>();
	
	static {
		init();
	}
	
	private static void init() {
		setConfig.add("/user");
	}
	
	public static Set<String> getUrlPatterns(){
		return setConfig;
	}
}
