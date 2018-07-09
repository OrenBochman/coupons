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

//@WebFilter("/rest/*")
public class BuildFilter implements Filter {

	private final static Logger LOG = LoggerFactory.getLogger(BuildFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		LOG.info("Initializing filter :{}", this);		
	}


	@Override
	public void destroy() {
		LOG.warn("Destructing filter :{}", this);
	}
	

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		Cookie[] cookies = req.getCookies();
		
		String buildToken = getBuildToken(cookies);
		
		if(isBuildCurrent(buildToken)) {
			chain.doFilter(request, response);
			return;
		}
		
		HttpServletResponse res  = (HttpServletResponse) response;
		res.setStatus(401);
		
	}


	private boolean isBuildCurrent(String buildToken) {
		// TODO Auto-generated method stub
		return false;
	}

	private String getBuildToken(Cookie[] cookies) {
		// TODO Auto-generated method stub
		return null;
	}

}
