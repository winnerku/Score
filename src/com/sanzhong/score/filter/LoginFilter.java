package com.sanzhong.score.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.sanzhong.score.pojo.User;
import com.sanzhong.score.service.UserService;
import com.sanzhong.score.util.MD5Util;

public class LoginFilter extends HttpServlet implements Filter {

	private UserService userService;
	private static final long serialVersionUID = 1L;
	private FilterConfig filterConfig;
	private String noFilter;
	private String noLogin;
	protected Logger logger = Logger.getLogger(LoginFilter.class);

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		res.setHeader("Cache-Control", "no-cache");
		res.setHeader("Pragma", "no-cache");
		res.setDateHeader("Expires", -1);
		String url = req.getRequestURI();
		for (String filter : noFilter.split(",")) {
			if (url.indexOf(filter) > -1) {
				chain.doFilter(req, res);
				return;
			}
		}
		if (url.indexOf("/login.json") > -1) {
			try {
				String username = req.getParameter("username");
				String password = req.getParameter("password");
				User user = null;
				if (username != null && !"".equals(username))
					user = userService.getUser(username);
				if (user == null) {
					res.sendRedirect(noLogin);
					return;
				} else {
					String md5 = MD5Util.getMD5((password + "{" + username + "}").getBytes());
					if (md5.equals(user.getPass())) {
						session.setAttribute("username", user.getName());
						chain.doFilter(req, res);
					} else {
						res.sendRedirect(noLogin);
						return;
					}
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		} else {
			if (session.getAttribute("username") != null && session.getAttribute("username").toString().length() != 0) {
				chain.doFilter(req, res);
			} else {
				res.sendRedirect(noLogin);
			}
			return;
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
		WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(filterConfig.getServletContext());
		userService = context.getBean(UserService.class);
		noFilter = this.filterConfig.getInitParameter("noFilter");
		noLogin = this.filterConfig.getInitParameter("noLogin");
	}
}
