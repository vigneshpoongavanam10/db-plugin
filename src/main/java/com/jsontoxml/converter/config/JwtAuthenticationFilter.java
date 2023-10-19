package com.jsontoxml.converter.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;

    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

        /*(STEP 1)


         * This is the JwtFilter which is the first step in the spring security
         * The first step in this process is that the filter will check for the token in the request so,
         * we will extract the  token from the request first.
         *
         * authHeader---> the token send via the header whose name is the "Authorization". so first we need to get the header
         * then check the header is null or the name starts with the "Bearer"
         *
         * Jwt------>after extracting and checking the header now we extract the token
         *  */

        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;
        if (authHeader == null || !authHeader.startsWith("Bearer")) {
            filterChain.doFilter(request, response);
            return;
        }
        jwt = authHeader.substring(7);

        /*(STEP 2)
         *
         * After validating the  jwt token we need to call the USerServiceDetails(interface) to check whether the user is present inside the database or not
         * so we need to extract the username or email from the jwt token*/
        userEmail = jwtService.extractUsername(jwt);

        /*
         * check the user existence in the database
         * if the user is not yet  authenticated ---to do that we have securitycontextholder obj gives whether the user is not authenticated or not */

        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail); /*loadbyusername method is in the applicationConfig file*/
            if (jwtService.isTokenValid(jwt, userDetails))        /*if the token is valid then we need to update the securityContextHolder theat the update thet the user is authenticatede*/ {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}

/*
 * After this process we need to tell the spring security to use our jwt filter , in order to do this we need to config our  jwt filter
 * */

