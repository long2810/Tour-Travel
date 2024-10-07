package com.example.my_project2.token;

import com.example.my_project2.user.dto.UserDto;
import com.example.my_project2.user.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@RequiredArgsConstructor
public class TokenFilterHandler extends OncePerRequestFilter {
    private final TokenUtils tokenUtils;
    private final UserService userService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        if (header == null){
            filterChain.doFilter(request, response);
            return;
        }

        String[] headSplit = header.split(" ");
        if (!(headSplit.length==2 && headSplit[0].equals("Bearer"))){
            filterChain.doFilter(request, response);
            return;
        }

        String token = headSplit[1];
        if (!tokenUtils.validate(token)){
            filterChain.doFilter(request, response);
            return;
        }

        String username = tokenUtils.parsetClaims(token).getSubject();
        UserDto user = (UserDto) userService.loadUserByUsername(username);

        SecurityContext context = SecurityContextHolder.createEmptyContext();

        AbstractAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(
                        user,
                        user.getPassword(),
                        user.getAuthorities()
                );
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);
        filterChain.doFilter(request, response);
    }
}
