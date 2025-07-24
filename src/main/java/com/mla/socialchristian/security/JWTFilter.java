package com.mla.socialchristian.security;

import com.mla.socialchristian.configurations.SecurityConfiguration;
import com.mla.socialchristian.configurations.WebSecurityConfiguration;
import com.mla.socialchristian.domain.interfaces.repository.IBlacklistTokenRepository;
import io.jsonwebtoken.JwtException;
import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;

public class JWTFilter extends OncePerRequestFilter {

    private final IBlacklistTokenRepository blacklistTokenRepository;

    public JWTFilter(IBlacklistTokenRepository blacklistTokenRepository) {
        this.blacklistTokenRepository = blacklistTokenRepository;
    }

    @Override
    protected void doFilterInternal(@Nonnull HttpServletRequest request, @Nonnull HttpServletResponse response, @Nonnull FilterChain filterChain) {
        try {
            if (Arrays.stream(WebSecurityConfiguration.USER_AUTH).noneMatch(request.getRequestURI()::startsWith)){
                filterChain.doFilter(request, response);
                return;
            }

            String token = request.getHeader(JWTCreator.HEADER_AUTHORIZATION);
            var tokenExists = token != null && !token.isEmpty();
            if (tokenExists)
                token = token.split(" ")[1];

            if (tokenExists && !blacklistTokenRepository.existsByToken(token)) {
                var user = JWTCreator.create(token, SecurityConfiguration.KEY);
                var authorities = getAuthorities(user.getRoles());

                var userToken = new UsernamePasswordAuthenticationToken(user, token, authorities);
                SecurityContextHolder.getContext().setAuthentication(userToken);
            }
            else{
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                return;
            }

            filterChain.doFilter(request,response);
        } catch (GeneralSecurityException | JwtException e){
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private List<SimpleGrantedAuthority> getAuthorities(List<String> roles){
        return roles
                .stream()
                .map(SimpleGrantedAuthority::new)
                .toList();
    }
}
