package com.twoclothing.filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.Key;
import java.security.KeyStore;
import java.util.Map;
import java.util.Set;

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
            // 儲存請求參數
            Map<String, String[]> filterParameterMap = request.getParameterMap();
            request.getSession().setAttribute("filterParameterMap", filterParameterMap);
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
            Object filterParameterMap = session.getAttribute("filterParameterMap");
            session.removeAttribute("filterParameterMap");
            if (filterParameterMap != null) {
                Map<String, String[]> currentMap = (Map<String, String[]>) filterParameterMap;
                Set<String> keySet = currentMap.keySet();
                for (String key : keySet) {
                    request.setAttribute(key, currentMap.get(key)[0]);
                }
                String location = (String) session.getAttribute("location");
                System.out.println(location);
                System.out.println(123);
                // 轉發回上一頁
                String realPath = extractPathAfterSecondSlash(location);
                request.getRequestDispatcher(realPath).forward(request, response);
            } else {
                System.out.println(456);
                chain.doFilter(request, response);
            }
        }
    }

    public String extractPathAfterSecondSlash(String originalPath) {
        int secondSlashIndex = originalPath.indexOf('/', originalPath.indexOf('/') + 1);
        if (secondSlashIndex != -1) {
            return originalPath.substring(secondSlashIndex);
        } else {
            return originalPath;
        }
    }
}
