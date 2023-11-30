package com.twoclothing.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;


public class SetRequestCharacterEncodingFilter implements Filter {
    private String characterEncoding;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.characterEncoding = filterConfig.getInitParameter("characterEncoding");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        request.setCharacterEncoding(characterEncoding);
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        characterEncoding = null;
    }
}
