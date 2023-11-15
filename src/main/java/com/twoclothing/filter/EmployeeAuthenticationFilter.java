package com.twoclothing.filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter("/back_end/*")
public class EmployeeAuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        HttpSession session = request.getSession();
        Object emp = session.getAttribute("emp");
        
        String ErrorMsg="";
        
        if (emp == null) {
            ErrorMsg = "帳號已登出,請重新登入";
        }
        
        if("".equals(ErrorMsg)) {
        	chain.doFilter(request, response);
        }else {
            String alertScript = "<script>"
                    + "var userConfirmed = confirm('"+ErrorMsg+"');"
                    + "window.location.href = '" + request.getContextPath() + "/empLogin.html';"
                    + "</script>";
            out.println(alertScript);
        }
    	
        
    }
}
