package com.fearrux.filter;

import com.fearrux.exception.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter(value = "/*")
public class ExceptionHandlerFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (InvalidMatchException | InvalidNameException | InvalidPlayerIdException | InvalidUuidException |
                 MatchDoesNotExistException exception) {
            HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
            HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
            httpServletRequest.getSession().setAttribute("error", exception.getMessage());
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/new-match");
        }
    }
}
