package com.ssafy.billige.authentication.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HeaderFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        final HttpServletResponse res = (HttpServletResponse) response;
        res.setHeader("Access-Control-Allow-Origin", "*");
        res.setHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
        res.setHeader("Access-Control-Max-Age", "3601");
        res.setHeader(
                "Access-Control-Allow-Headers",
                "X-Requested-With, Content-Type, Authorization, X-XSRF-token"
        );
        res.setHeader("Access-Control-Allow-Credentials", "false");
        res.setHeader("Access-Control-Expose-Headers", "Authorization");
        chain.doFilter(request, response);
    }
}
