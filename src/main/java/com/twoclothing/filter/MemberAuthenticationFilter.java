package com.twoclothing.filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("*.check")
public class MemberAuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        // 取得session
        HttpSession session = request.getSession();
        // 取得user物件
        Object user = session.getAttribute("user");
        if (user == null) {
            // 物件不存在等於沒登入
            // 綁定發起請求的URI(保存上一頁)
            session.setAttribute("location", request.getRequestURI());
            // 重定向到登入頁面
            response.sendRedirect(request.getContextPath() + "/front_end/members/registerLogin.jsp");
        } else {
            chain.doFilter(request, response);
        }
    }
}
