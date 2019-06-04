package cn.yao.bookStore.web.filter;

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

import cn.yao.bookStore.domain.User;
import cn.yao.bookStore.service.UserService;

@WebFilter("/AutoLoginFilter")
public class AutoLoginFilter implements Filter {
	public AutoLoginFilter() {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		// 获取一个名为autoLogin的cookie
		Cookie[] cookies = request.getCookies();
		String autologin = null;
		for (int i = 0; i < cookies.length; i++) {
			if (cookies[i].getName().equals("autologin")) {
				// 找到指定的cookie
				autologin = cookies[i].getValue();
				System.out.println("AutoLoginFilter autologin:" + autologin);
				break;
			}
		}
		if (autologin != null) {
			// 自动登录
			String[] parts = autologin.split("#");
			String name = parts[0];
			String pwd = parts[1];
			UserService userService=new UserService();
			try {
				User user=userService.login(name, pwd);
				request.getSession().setAttribute("User", user);
				//获取用户的角色，普通用户或超级用户
//				String role = user.getRole();
				
//				//如果是超级用户，就进入到网上书城的后台管理系统，否则进入我的账户页面
//				if("超级用户".equals(role)) {
//					response.sendRedirect(request.getContextPath()+"/admin/login/home.jsp");
//					return;
//				}else {
//					response.sendRedirect(request.getContextPath()+"/client/myAccount.jsp");
//					return;
//				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
