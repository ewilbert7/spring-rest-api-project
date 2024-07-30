package com.wilbert.springboot.springsecuritydemo.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
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
    protected void doFilterInternal(
           @NonNull HttpServletRequest request,
           @NonNull HttpServletResponse response,
           @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        //get authorization header from the request
      final String authHeader = request.getHeader("Authorization");
      final String jwt;
      final String userEmail;
      //Check Jwt Token
      if (authHeader == null || !authHeader.startsWith("Bearer ")) {
          filterChain.doFilter(request, response);
          return;
      }
      /*Because the token will be in the format ('Bearer XXXXXXXX')
      we start reading from the 7th position*/
      jwt = authHeader.substring(7);
      userEmail = jwtService.extractUsername(jwt);
      //checks if the email was successfully extracted from the token
        //then checks if the user has already been authenticated
      if(userEmail != null &&
              SecurityContextHolder.getContext().
                      getAuthentication() == null) {
          UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
          if(jwtService.isTokenValid(jwt, userDetails)) {
              //creates the authToken to update the SecurityContext
              UsernamePasswordAuthenticationToken authToken= new UsernamePasswordAuthenticationToken(
                      userDetails,
                      null,
                      userDetails.getAuthorities()
              );

              authToken.setDetails(
                      new WebAuthenticationDetailsSource().buildDetails(request)
              );
              SecurityContextHolder.getContext().setAuthentication(authToken);

          }
          //pass the hand to the next filters to be executed
          filterChain.doFilter(request, response);
      }
    }
}
