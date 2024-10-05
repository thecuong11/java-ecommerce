package com.example.javaEcommerce.security.filter;

import com.example.javaEcommerce.common.Constant;
import com.example.javaEcommerce.config.SecurityConfig;
import com.example.javaEcommerce.dto.ResponseDto;
import com.example.javaEcommerce.enums.MessageEnum;
import com.example.javaEcommerce.enums.TokenType;
import com.example.javaEcommerce.exceptions.AppException;
import com.example.javaEcommerce.utils.JwtUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


@Log4j2
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService userDetailsService;

    private final AntPathMatcher pathMatcher = new AntPathMatcher();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try{
            String token = this.getJwtFromRequest(request);
            if (StringUtils.isEmpty(token) || !JwtUtils.tokenIsValid(token)) {
                throw new AppException("Token invalid");
            }


            String userId = JwtUtils.getFromJwt(token, Constant.PAYLOAD_USER_ID);
            String email = JwtUtils.getFromJwt(token, Constant.PAYLOAD_EMAIL);
            if (StringUtils.isEmpty(userId)  || StringUtils.isEmpty(email)) {
                throw new AppException("Not found info from token");
            }

            UserDetails userDetails = userDetailsService.loadUserByUsername(email);
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    userDetails, userDetails.getUsername(), userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);

        } catch (Exception e) {
            log.error("JwtAuthenticationFilter doFilterInternal ERROR : {}", e.getMessage());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setStatus(HttpStatus.UNAUTHORIZED.value());

            ResponseDto<?> responseError = new ResponseDto<>();
            responseError.setStatus(Constant.ERROR);
            responseError.setMessage(MessageEnum.ERR_INVALID_TOKEN.getMessage());
            responseError.setCode(MessageEnum.ERR_INVALID_TOKEN.getCode());
            new ObjectMapper().writeValue(response.getOutputStream(), responseError);
            return;
        }
        filterChain.doFilter(request, response);
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.isNotBlank(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String requestPath = request.getRequestURI();
        // nếu requestPath khớp pattern với ít nhất 1 path trong WHITE_LIST_API thì không chạy vào doFilterInternal
        return Arrays.stream(SecurityConfig.WHITE_LIST_API).anyMatch(e -> pathMatcher.match(e, requestPath));
    }
}
