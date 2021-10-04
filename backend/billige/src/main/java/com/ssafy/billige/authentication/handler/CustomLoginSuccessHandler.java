package com.ssafy.billige.authentication.handler;

import com.ssafy.billige.user.domain.CustomUserDetails;
import com.ssafy.billige.user.domain.User;
import com.ssafy.billige.utils.StringUtils;
import com.ssafy.billige.utils.TokenUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void onAuthenticationSuccess(final HttpServletRequest request, final HttpServletResponse response,
                                        final Authentication authentication) throws IOException {

        final User user = ((CustomUserDetails) authentication.getPrincipal()).getUser();
        final String token = TokenUtils.generateJwtToken(user);
        response.addHeader(StringUtils.AUTH_HEADER, StringUtils.TOKEN_TYPE + " " + token);
        response.getWriter().write("success");
//		getRedirectStrategy().sendRedirect(request, response, "/");

    }
}
