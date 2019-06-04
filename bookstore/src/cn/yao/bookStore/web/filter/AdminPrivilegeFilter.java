package cn.yao.bookStore.web.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.yao.bookStore.domain.User;

@WebFilter("/AdminPrivilegeFilter")
public class AdminPrivilegeFilter implements Filter {

    public AdminPrivilegeFilter() {
    }

    public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//1\强制转换
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		//2\判断是否具有权限
		User user = (User) httpServletRequest.getSession().getAttribute("user");
		if(user != null && user.getRole().equals("超级用户")) {
			//如果是超级用户，就有后台权限，不用管
			chain.doFilter(httpServletRequest, httpServletResponse);
		}
		//如果是普通用户进行过滤
		httpServletResponse.sendRedirect(httpServletRequest.getContextPath()+"/error/privilege,jsp");
		
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
