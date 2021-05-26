package com.copious.training.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Stream;

//@PropertySource(value = "classpath:application.properties")
@Component
public class AuthenticationFilter extends OncePerRequestFilter {
    @Value(value = "${token}")
    String jwtToken;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        try {
            final String uri = request.getRequestURI();

            if (uri.contains("swagger") || uri.contains("v2") || uri.contains("signup") || uri.contains("user")) {
                System.out.println("WT-AUTH: Skipping token validation for {}");

                chain.doFilter(request, response);
            } else {

                jwtToken = extractJwtFromRequest(request);
                // UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(jwtTokenUtil.getUsernameFromToken(jwtToken));
                if ("megh".equals(jwtToken)) {
                    System.out.println("Authorization success");
                    chain.doFilter(request, response);
                } else {
                    System.out.println("failed");
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Missing authentication token.");
                }
            }
        } catch (Exception ex) {

            System.out.println("JWT-AUTH:  Wrong JWT Token.");
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Wrong JWT Token. JWT strings must contain exactly 2 period characters. Found: 0");
        }
    }

    private String extractJwtFromRequest(HttpServletRequest request) {
        return Stream.of(request)
                .map(req -> req.getHeader("Authorization"))
                .filter(token -> StringUtils.hasText(token))
                .findFirst()
                .orElse(null);
    }
}