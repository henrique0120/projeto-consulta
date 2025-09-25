package br.com.henrique.medicalclinic.security;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class JWTFilter extends OncePerRequestFilter {

    private final JWTCreator jwtCreator;

    private final JWTConfig jwtConfig;

    public JWTFilter(JWTCreator jwtCreator, JWTConfig config) {
        this.jwtCreator = jwtCreator;
        this.jwtConfig = config;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = request.getHeader("Authorization");
        String path = request.getRequestURI();

        try {
            if (token != null && !token.isEmpty()) {

                if (path.equals("/register") || path.equals("/login")) {
                    filterChain.doFilter(request, response);
                    return;
                }

                JWTObject jwtObject = jwtCreator.parseToken(token);

                List<SimpleGrantedAuthority> authorities = jwtObject.getRoles().stream()
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

                var authentication = new UsernamePasswordAuthenticationToken(
                        jwtObject.getSubject(), null, authorities);

                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                SecurityContextHolder.clearContext();
            }

            filterChain.doFilter(request, response);
        } catch (JwtException e) {
            e.printStackTrace();
            response.setStatus(HttpStatus.FORBIDDEN.value());
        }
    }
}
