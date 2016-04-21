package pl.com.importer.config.security.handler;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Interface AjaxAuthenticationFailureHandler
 *
 * @author Artur Olbromski artur.olbromski@gmail.pl
 */
public interface AjaxAuthenticationFailureHandler extends AuthenticationFailureHandler {

    void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                 AuthenticationException exception)
            throws IOException, ServletException;

}