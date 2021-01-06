package amvisible.hookahmarket.web.security;

import amvisible.hookahmarket.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static amvisible.hookahmarket.data.constant.Constant.AUTHORIZATION_HEADER_NAME;
import static amvisible.hookahmarket.data.constant.Constant.AUTHORIZATION_TOKEN_TYPE;

@Component
public class AuthenticationJwtTokenFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;
    private final UserService userService;

    @Autowired
    public AuthenticationJwtTokenFilter(JwtUtils jwtUtils, UserService userService) {
        this.jwtUtils = jwtUtils;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            FilterChain filterChain) throws ServletException, IOException {

        String jwtToken = parseJwt(httpServletRequest);

        if (null != jwtToken && this.jwtUtils.validateJwtToken(jwtToken)) {

            String email = this.jwtUtils.getEmailFromJwtToken(jwtToken);
            UserDetails userDetails = this.userService.loadUserByUsername(email);

            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());

            authentication.setDetails(
                    new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));

            SecurityContextHolder.getContext().setAuthentication(authentication);

        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private String parseJwt(HttpServletRequest httpServletRequest) {
        String authorizationHeader = httpServletRequest.getHeader(AUTHORIZATION_HEADER_NAME);

        if (StringUtils.hasText(authorizationHeader) && authorizationHeader.startsWith(AUTHORIZATION_TOKEN_TYPE)) {
            return authorizationHeader.substring(7);
        }
        return null;
    }
}
