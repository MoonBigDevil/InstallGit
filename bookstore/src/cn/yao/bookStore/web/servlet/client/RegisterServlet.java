package cn.yao.bookStore.web.servlet.client;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import cn.yao.bookStore.domain.User;
import cn.yao.bookStore.exception.RegisterException;
import cn.yao.bookStore.service.UserService;
import cn.yao.bookStore.utils.ActiveCodeUtils;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public RegisterServlet() {
        super();
    }

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.setContentType("text/html;charset=utf-8");
//		request.setCharacterEncoding("utf-8");
		//校验数据
		
		//将表单提交给JavaBean
		User user = new User();
		try {
			//将表单数据封装到user对象对应的属性中
			BeanUtils.populate(user, request.getParameterMap());
			//封装激活码
			user.setActiveCode(ActiveCodeUtils.createActiveCode());
			
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		System.out.println(user.getUsername());
		
		//调用service完成注册操作
		UserService service = new UserService();
		try {
			service.register(user);
		} catch (RegisterException e) {
			e.printStackTrace();
			response.getWriter().write(e.getMessage());
			return;
		}
		//注册成功，跳转到registersuccess.jsp
		response.sendRedirect(request.getContextPath()+"/client/registersuccess.jsp");
	}
	
}
