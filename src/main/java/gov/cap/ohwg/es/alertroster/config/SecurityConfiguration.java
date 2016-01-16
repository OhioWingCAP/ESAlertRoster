package gov.cap.ohwg.es.alertroster.config;

import gov.cap.ohwg.es.alertroster.config.security.AuthoritiesConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.query.spi.EvaluationContextExtension;
import org.springframework.data.repository.query.spi.EvaluationContextExtensionSupport;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ResourceBundle;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private static Logger LOG = LoggerFactory.getLogger(SecurityConfiguration.class);

    private static String ctLogoutUrl;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("test.user@ohwg.cap.gov")
                .password("THrQ;3E8f#4vcKF").roles("group1esofficer");
    }

    // @formatter:off
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf()
                .disable()
            .headers()
                .frameOptions().sameOrigin()
                .and()
            .authorizeRequests()
                .antMatchers("app/img/**").anonymous()
                .anyRequest().authenticated()
                .antMatchers("/admin/**").hasAuthority(AuthoritiesConstants.ADMIN)
                .and()
            .formLogin()
                .permitAll()
                .and()
            .logout()
                .deleteCookies(Constants.COOKIE_NAME)
                .permitAll();
    }
    // @formatter:on

    /**
     * This allows SpEL support in Spring Data JPA @Query definitions.
     * <p/>
     * See https://spring.io/blog/2014/07/15/spel-support-in-spring-data-jpa-query-definitions
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
                return new SecurityExpressionRoot(SecurityContextHolder.getContext().getAuthentication()) {
                };
            }
        };
    }

}
