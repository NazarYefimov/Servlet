package org.example;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

@WebFilter("/time")
public class TimezoneValidateFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String timezone = request.getParameter("timezone");

        if (isValidTimezone(timezone)) {
            chain.doFilter(request, response);
        } else {
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.setContentType("text/html;charset=UTF-8");
            httpResponse.getWriter().write("Invalid timezone");
            httpResponse.setStatus(400);
        }
    }

    private boolean isValidTimezone(String timezone) {
        try {
            java.util.TimeZone.getTimeZone(timezone);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void init(FilterConfig fConfig) {
    }

    @Override
    public void destroy() {
    }

}