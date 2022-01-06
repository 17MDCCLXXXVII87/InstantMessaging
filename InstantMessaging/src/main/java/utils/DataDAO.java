package utils;

import java.util.HashMap;
import java.util.Map;

import bean.User;

public class DataDAO {
	
	private static Map<String, User> mapUsers = new HashMap<String, User>();
	
	public static User findUser(String email, String password) {
		User u = mapUsers.get(email);
		
		if(u != null && u.getPassword().equals(password)) {
			return u;
		}
		return null;
	}
}
