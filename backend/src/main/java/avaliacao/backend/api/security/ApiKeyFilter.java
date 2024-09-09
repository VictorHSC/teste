package avaliacao.backend.api.security;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ApiKeyFilter implements Filter {

    private final String apiKey;

    public ApiKeyFilter(String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public void init(FilterConfig filterConfig) {
        // Override
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String key = httpRequest.getHeader("X-API-KEY");

        if (this.apiKey.equals(key)) {
            chain.doFilter(request, response);
        } else {
            response.getWriter().write("Unauthorized");
            ((HttpServletResponse) response).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

    @Override
    public void destroy() {
        // Override
    }
}
