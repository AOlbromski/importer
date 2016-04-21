package pl.com.importer.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.repository.query.spi.EvaluationContextExtension;
import org.springframework.data.repository.query.spi.EvaluationContextExtensionSupport;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import pl.com.importer.config.security.entrypoint.Http401UnauthorizedEntryPoint;
import pl.com.importer.config.security.filter.CsrfCookieGeneratorFilter;
import pl.com.importer.config.security.handler.AjaxAuthenticationFailureHandler;
import pl.com.importer.config.security.handler.AjaxAuthenticationSuccessHandler;
import pl.com.importer.config.security.handler.AjaxLogoutSuccessHandler;
import pl.com.importer.config.security.util.AuthoritiesConstants;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private Environment env;

    @Autowired
    private AjaxAuthenticationSuccessHandler ajaxAuthenticationSuccessHandler;

    @Autowired
    private AjaxAuthenticationFailureHandler ajaxAuthenticationFailureHandler;

    @Autowired
    private AjaxLogoutSuccessHandler ajaxLogoutSuccessHandler;

    @Autowired
    private Http401UnauthorizedEntryPoint authenticationEntryPoint;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private RememberMeServices rememberMeServices;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/scripts/**/*.{js,html}").antMatchers("/bower_components/**")
                .antMatchers("/i18n/**").antMatchers("/assets/**").antMatchers("/swagger-ui/**")
                .antMatchers("/test/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().requireCsrfProtectionMatcher(this.requestMatcher()).and()
                .addFilterAfter(new CsrfCookieGeneratorFilter(), CsrfFilter.class)
                .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint).and()
                .rememberMe().rememberMeServices(rememberMeServices)
                .key(env.getProperty("spring.security.rememberme.key")).and().formLogin()
                .loginProcessingUrl("/api/authentication")
                .successHandler(ajaxAuthenticationSuccessHandler)
                .failureHandler(ajaxAuthenticationFailureHandler).usernameParameter("j_username")
                .passwordParameter("j_password").permitAll().and().logout().logoutUrl("/api/logout")
                .logoutSuccessHandler(ajaxLogoutSuccessHandler).deleteCookies("JSESSIONID")
                .permitAll().and().headers().frameOptions().disable().authorizeRequests().antMatchers("/api/item**", "/api/item/**").permitAll()
                .antMatchers("/api/offers**","/api/offers/**").permitAll().antMatchers("/api/register").permitAll()
                .antMatchers("/api/register/companies").permitAll()
                .antMatchers("/api/activate").permitAll().antMatchers("/api/authenticate")
                .permitAll().antMatchers("/api/logs/**").hasAuthority(AuthoritiesConstants.ADMIN)
                .antMatchers("/api/**").authenticated().antMatchers("/rest/**").permitAll().antMatchers("/metrics/**")
                .hasAuthority(AuthoritiesConstants.ADMIN).antMatchers("/health/**")
                .hasAuthority(AuthoritiesConstants.ADMIN).antMatchers("/trace/**")
                .hasAuthority(AuthoritiesConstants.ADMIN).antMatchers("/dump/**")
                .hasAuthority(AuthoritiesConstants.ADMIN).antMatchers("/shutdown/**")
                .hasAuthority(AuthoritiesConstants.ADMIN).antMatchers("/beans/**")
                .hasAuthority(AuthoritiesConstants.ADMIN).antMatchers("/configprops/**")
                .hasAuthority(AuthoritiesConstants.ADMIN).antMatchers("/info/**")
                .hasAuthority(AuthoritiesConstants.ADMIN).antMatchers("/autoconfig/**")
                .hasAuthority(AuthoritiesConstants.ADMIN).antMatchers("/env/**")
                .hasAuthority(AuthoritiesConstants.ADMIN).antMatchers("/trace/**")
                .hasAuthority(AuthoritiesConstants.ADMIN).antMatchers("/api-docs/**")
                .hasAuthority(AuthoritiesConstants.ADMIN).antMatchers("/protected/**")
                .authenticated();

    }

    /**
     * This allows SpEL support in Spring Data JPA @Query definitions. <p/> See
     * https://spring.io/blog/2014/07/15/spel-support-in-spring-data-jpa-query-definitions
     */
    @Bean
    EvaluationContextExtension securityExtension() {
        return new EvaluationContextExtensionSupport() {
            @Override
            public String getExtensionId() {
                return "security";
            }

            @Override
            public SecurityExpressionRoot getRootObject() {
                return new SecurityExpressionRoot(
                        SecurityContextHolder.getContext().getAuthentication()) {
                };
            }
        };
    }

    @Bean
    public RequestMatcher requestMatcher() {
        return new RequestMatcher() {
            private Pattern allowedMethods =
                    Pattern.compile("^(GET|HEAD|TRACE|OPTIONS|POST|DELETE|PUT)$");
            private RegexRequestMatcher unprotectedMatcher =
                    new RegexRequestMatcher("/api/offer.*", null);

            @Override
            public boolean matches(final HttpServletRequest request) {
                return !allowedMethods.matcher(request.getMethod()).matches() && !unprotectedMatcher
                        .matches(request);
            }
        };
    }

    @EnableGlobalMethodSecurity(prePostEnabled = true, jsr250Enabled = true)
    private static class GlobalSecurityConfiguration extends GlobalMethodSecurityConfiguration {

    }

}
