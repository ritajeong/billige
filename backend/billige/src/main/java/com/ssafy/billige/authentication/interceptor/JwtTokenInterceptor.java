package com.ssafy.billige.authentication.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import com.ssafy.billige.utils.StringUtils;
import com.ssafy.billige.utils.TokenUtils;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class JwtTokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) throws IOException {

        final String header = request.getHeader(StringUtils.AUTH_HEADER);

        if (header != null) {
            final String token = TokenUtils.getTokenFromHeader(header);
            if (TokenUtils.isValidToken(token)) {
                return true;
            }
        }

        response.sendRedirect("/error/unauthorized");

        return false;
    }
}