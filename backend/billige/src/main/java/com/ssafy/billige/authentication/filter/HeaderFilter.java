package com.ssafy.billige.authentication.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class HeaderFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        final HttpServletResponse res = (HttpServletResponse) response;
        res.setHeader("Access-Control-Allow-Origin", "*");
        res.setHeader("Access-Control-Allow-Methods", "*");
        res.setHeader("Access-Control-Max-Age", "3601");
        res.setHeader(
                "Access-Control-Allow-Headers",
                "X-Requested-With, Content-Type, Authentication, X-XSRF-token"
        );
        res.setHeader("Access-Control-Allow-Credentials", "false");
        res.setHeader("Access-Control-Expose-Headers", "Authentication");
        chain.doFilter(request, response);
    }
}
