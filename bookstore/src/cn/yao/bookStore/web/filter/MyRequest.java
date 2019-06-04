package cn.yao.bookStore.web.filter;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class MyRequest extends HttpServletRequestWrapper {

	private HttpServletRequest request;
	private boolean hasEncode;
	public MyRequest(HttpServletRequest request) {
		super(request);
		this.request = request;
	}
	
	//对需要增强的方法进行覆盖
	@Override
	public Map<String, String[]> getParameterMap() {
		//获取请求方式
		String method = request.getMethod();
		if(method.equalsIgnoreCase("post")) {
			//post请求
			try {
				request.setCharacterEncoding("utf-8");
				return request.getParameterMap();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			
		}else if(method.equalsIgnoreCase("get")) {
			//get请求
			Map<String, String[]> parameterMap = request.getParameterMap();
			if(!hasEncode) {//确保get手动编码只执行一次
				for(String parameterName : parameterMap.keySet()) {
					//keySet 返回此映射中包含键的Set视图
					String[] values = parameterMap.get(parameterName);
					if(values != null){
						for(int i=0 ; i<values.length;i++) {
							try {
								values[i] = new String(values[i].getBytes("ISO-8859-1"), "utf-8");
							} catch (UnsupportedEncodingException e) {
								e.printStackTrace();
							}
						}
					}
				}
				hasEncode = true;
			}
			return parameterMap;
		}
		return super.getParameterMap();
	}
	
	
	public String getParameter(String name) {
		Map<String, String[]> parameterMap = getParameterMap();
		String[] values = parameterMap.get(name);
		if(values == null) {
			return null;
		}
		return values[0];
	}

	
	public String[] getParameterValues(String name) {
		Map<String, String[]> parameterMap = getParameterMap();
		String[] values = parameterMap.get(name);
		return values;
	}
}
