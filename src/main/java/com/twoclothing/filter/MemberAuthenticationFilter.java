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
        // 物件不存在等於沒登入
        if (user == null) {
            // 綁定發起請求的URI(保存上一頁)
            session.setAttribute("location", request.getRequestURI());
            // 判斷是不是Ajax請求
            //  獲得請求頭部中X-Requested-With的值
            //  當前端發起AJAX請求時,瀏覽器會自動在HTTP請求頭部添加"X-Requested-With: XMLHttpRequest"
            String ajaxHeader = request.getHeader("X-Requested-With");
            if ("XMLHttpRequest".equals(ajaxHeader)) {
                // 是Ajax請求,設置HTTP響應的狀態碼為403
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            } else {
                // 重定向到登入頁面
                response.sendRedirect(request.getContextPath() + "/front_end/members/registerLogin.jsp");
            }
        } else {
            chain.doFilter(request, response);
        }
    }
}