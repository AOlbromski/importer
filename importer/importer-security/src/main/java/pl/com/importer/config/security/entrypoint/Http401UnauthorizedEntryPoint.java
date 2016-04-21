package pl.com.importer.config.security.entrypoint;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Returns a 401 error code (Unauthorized) to the client.
 */
public interface Http401UnauthorizedEntryPoint extends AuthenticationEntryPoint {

    /**
     * Always returns a 401 error code to the client.
     */
    void commence(HttpServletRequest request, HttpServletResponse response,
                  AuthenticationException arg2) throws IOException, ServletException;

}
