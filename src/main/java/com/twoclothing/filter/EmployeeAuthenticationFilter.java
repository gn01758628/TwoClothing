package com.twoclothing.filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebFilter("/back_end/*")
public class EmployeeAuthenticationFilter implements Filter {
	
	private ServletContext servletContext;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	    servletContext = filterConfig.getServletContext();
	    // 在這裡使用 servletContext 進行初始化
	}
	

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        String requestedWithHeader = request.getHeader("X-Requested-With");

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        HttpSession session = request.getSession();
        Object empId = session.getAttribute("empId");
        
        String ErrorMsg="";
        
        if (empId == null) {
            ErrorMsg = "帳號已登出,請重新登入";
        }
        
        List<Integer> resignedEmployeeList = (List<Integer>) servletContext.getAttribute("resignedEmployeeList");
        
        if(resignedEmployeeList.contains(empId)) {
        	ErrorMsg = "您已離職,已執行登出";
        }
        
        if("".equals(ErrorMsg)) {
        	chain.doFilter(request, response);
        }else{
        	response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        	out.write(ErrorMsg);
            String alertScript = "<script>"
                    + "var userConfirmed = confirm('"+ErrorMsg+"');"
                    + "window.location.href = '" + request.getContextPath() + "/empLogin.html';"
                    + "</script>";
            out.println(alertScript);
            out.flush();
        }
    	
        
    }
}
