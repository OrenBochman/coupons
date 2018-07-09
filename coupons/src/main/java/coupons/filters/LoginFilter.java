package coupons.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

//@Component
//Order(1)
//@WebFilter("/rest/*")
public class LoginFilter implements Filter {

	private final static Logger LOG = LoggerFactory.getLogger(LoginFilter.class);

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		String url = req.getRequestURI();
		if(url.endsWith("/login")) {
			chain.doFilter(request, response);
			return;
		}
		
		Cookie[] cookies = req.getCookies();

		String loginToken = getLoginToken(cookies);

		if(isLoginTokenExitsInCache(loginToken)) {
			chain.doFilter(request, response);
			return;
		}

		HttpServletResponse res  = (HttpServletResponse) response;
		res.setStatus(401);

	}

	private boolean isLoginTokenExitsInCache(String loginToken) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		LOG.info("Initializing filter :{}", this);		
	}


	@Override
	public void destroy() {
		LOG.warn("Destructing filter :{}", this);
	}

	String getLoginToken(Cookie[] cookies) {
		if(cookies==null) 
			return null;
		for(Cookie cookie: cookies) {
			if(cookie.getName().equals("LoginCookie"))
				return cookie.getValue();
		}
		return null;

	}
}
