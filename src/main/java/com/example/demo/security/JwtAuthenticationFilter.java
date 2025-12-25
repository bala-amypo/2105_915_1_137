// package com.example.demo.security;

// import jakarta.servlet.FilterChain;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;

// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
// import org.springframework.stereotype.Component;
// import org.springframework.web.filter.OncePerRequestFilter;

// import java.io.IOException;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;

// @Component
// public class JwtAuthenticationFilter extends OncePerRequestFilter {

//     private static final Logger logger =
//             LoggerFactory.getLogger(JwtAuthenticationFilter.class);

//     private final JwtUtil jwtUtil;
//     private final CustomUserDetailsService userDetailsService;

//     public JwtAuthenticationFilter(JwtUtil jwtUtil,
//                                    CustomUserDetailsService userDetailsService) {
//         this.jwtUtil = jwtUtil;
//         this.userDetailsService = userDetailsService;
//     }

//     // ✅ REQUIRED for Spring Security 6
//     @Override
//     protected boolean shouldNotFilter(HttpServletRequest request)
//             throws ServletException {

//         String path = request.getServletPath();

//         return path.startsWith("/v3/api-docs")
//                 || path.startsWith("/swagger-ui")
//                 || path.equals("/swagger-ui.html")
//                 || path.startsWith("/api/auth")
//                 || path.startsWith("/h2-console");
//     }

//     // ✅ MUST declare ServletException here
//     @Override
//     protected void doFilterInternal(HttpServletRequest request,
//                                     HttpServletResponse response,
//                                     FilterChain filterChain)
//             throws ServletException, IOException {

//         String requestTokenHeader = request.getHeader("Authorization");
//         String username = null;
//         String jwtToken = null;

//         if (requestTokenHeader != null &&
//                 requestTokenHeader.startsWith("Bearer ")) {

//             jwtToken = requestTokenHeader.substring(7);
//             try {
//                 username = jwtUtil.getUsernameFromToken(jwtToken);
//             } catch (Exception e) {
//                 logger.error("Unable to get JWT Token", e);
//             }
//         }

//         if (username != null &&
//                 SecurityContextHolder.getContext()
//                         .getAuthentication() == null) {

//             UserDetails userDetails =
//                     userDetailsService.loadUserByUsername(username);

//             if (jwtUtil.validateToken(jwtToken, userDetails)) {

//                 UsernamePasswordAuthenticationToken authentication =
//                         new UsernamePasswordAuthenticationToken(
//                                 userDetails,
//                                 null,
//                                 userDetails.getAuthorities()
//                         );

//                 authentication.setDetails(
//                         new WebAuthenticationDetailsSource()
//                                 .buildDetails(request)
//                 );

//                 SecurityContextHolder.getContext()
//                         .setAuthentication(authentication);
//             }
//         }

//         // ✅ This is why ServletException MUST be declared
//         filterChain.doFilter(request, response);
//     }
// }
package com.example.demo.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService userDetailsService;

    public JwtAuthenticationFilter(JwtUtil jwtUtil, CustomUserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String requestTokenHeader = request.getHeader("Authorization");
        String username = null;
        String jwtToken = null;

        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            jwtToken = requestTokenHeader.substring(7);
            try {
                username = jwtUtil.getUsernameFromToken(jwtToken);
            } catch (Exception e) {
                logger.error("Unable to get JWT Token");
            }
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

            if (jwtUtil.validateToken(jwtToken, userDetails)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        try {
            filterChain.doFilter(request, response);
        } catch (ServletException e) {
            throw e;
        }
    }
}