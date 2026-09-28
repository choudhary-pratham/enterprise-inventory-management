package com.pratham.devpilot.security;

import com.pratham.devpilot.entity.User;
import com.pratham.devpilot.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);

        if (!jwtService.isTokenValid(token)) {
            filterChain.doFilter(request, response);
            return;
        }

        String email = jwtService.extractEmail(token);

        User user = userRepository.findByEmail(email)
                .orElse(null);

        if (user == null || !user.isEnabled()) {
            filterChain.doFilter(request, response);
            return;
        }

        String role = user.getRole().name();
        System.out.println("USER: " + user.getEmail());
        System.out.println("ROLE FROM DB: " + user.getRole());
        System.out.println("AUTHORITY: ROLE_" + role);

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                user.getEmail(),
                null,
                java.util.List.of(
                        new SimpleGrantedAuthority("ROLE_" + role)));

        SecurityContextHolder
                .getContext()
                .setAuthentication(authentication);
        System.out.println(
                "AUTHORITIES: " +
                        SecurityContextHolder.getContext()
                                .getAuthentication()
                                .getAuthorities());
        filterChain.doFilter(request, response);
    }
}