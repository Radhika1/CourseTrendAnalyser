package edu.utd.ooad.cta.utils;

import javax.servlet.http.Cookie;

public class Utils {
	
	public static String getCookieValue(String key, Cookie[] cookies){
		String userId = null;
		for(Cookie c : cookies){
			if(c.getName().equals(key))
				userId = c.getValue();
		}
		
		return userId;
	}

}
