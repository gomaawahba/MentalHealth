//package com.example.gomaa.Config;
//
//import com.example.gomaa.Service.CustomUserDetailsService;
//import com.example.gomaa.Util.JwtUtil;
//import io.jsonwebtoken.ExpiredJwtException;
//import io.jsonwebtoken.JwtException;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.util.StringUtils;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//
//public class JwtAuthFilter extends OncePerRequestFilter {
//
//    private static final String AUTH_HEADER = "Authorization";
//    private static final String BEARER_PREFIX = "Bearer ";
//
//    @Autowired
//    private JwtUtil jwtUtil;
//
//    @Autowired
//    private CustomUserDetailsService customUserDetailsService;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request,
//                                    HttpServletResponse response,
//                                    FilterChain filterChain)
//            throws ServletException, IOException {
//        try {
//            String jwt = parseJwt(request);
//            if (jwt != null) {
//                setSecurityContext(request, jwt);
//            }
//        } catch (ExpiredJwtException ex) {
//            handleJwtException(response, "JWT expired", ex);
//            return;
//        } catch (JwtException | IllegalArgumentException ex) {
//            handleJwtException(response, "Invalid JWT", ex);
//            return;
//        }
//        filterChain.doFilter(request, response);
//    }
//
//    private String parseJwt(HttpServletRequest request) {
//        String headerAuth = request.getHeader(AUTH_HEADER);
//        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith(BEARER_PREFIX)) {
//            return headerAuth.substring(BEARER_PREFIX.length());
//        }
//        return null;
//    }
//
//    private void setSecurityContext(HttpServletRequest request, String jwt) {
//        String email = jwtUtil.extractEmail(jwt);
//        UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);
//
//        if (jwtUtil.isTokenValid(jwt, userDetails)) {
//            UsernamePasswordAuthenticationToken authentication =
//                    new UsernamePasswordAuthenticationToken(
//                            userDetails,
//                            null,
//                            userDetails.getAuthorities()
//                    );
//            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//        }
//    }
//
//    private void handleJwtException(HttpServletResponse response, String message, Exception ex)
//            throws IOException {
//        logger.error(message + ": {}");
//        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, message);
//    }
//}