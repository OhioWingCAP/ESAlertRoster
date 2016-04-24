package gov.cap.ohwg.es.alertroster.config;

import gov.cap.ohwg.es.alertroster.config.security.GaeAuthenticationFilter;
import gov.cap.ohwg.es.alertroster.config.security.GoogleAccountsAuthenticationEntryPoint;
import gov.cap.ohwg.es.alertroster.config.security.GoogleAccountsAuthenticationProvider;
import gov.cap.ohwg.es.alertroster.service.user.GaeDatastoreUserRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetailsByNameServiceWrapper;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.Filter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

//    private static Logger LOG = LoggerFactory.getLogger(SecurityConfiguration.class);
//
//    private static String ctLogoutUrl;

    @Autowired
    private GoogleAccountsAuthenticationEntryPoint googleAuth;

    @Autowired
    private GoogleAccountsAuthenticationProvider gaeAuthenticationProvider;

    @Autowired
    private GaeDatastoreUserRegistry gaeDatastoreUserRegistry;

    @Autowired
    private GaeAuthenticationFilter gaeAuthenticationFilter;

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("test.user@ohwg.cap.gov")
//                .password("THrQ;3E8f#4vcKF").roles("group1esofficer");
//    }


    @Bean
    public AuthenticationProvider getAuthenticationProvider() {
        return gaeAuthenticationProvider;
    }

    @Bean
    public AuthenticationManager getAuthenticationManager () {
        return new AuthenticationManager() {
            @Override
            public Authentication authenticate(Authentication authentication) throws AuthenticationException {
                return gaeAuthenticationProvider.authenticate(authentication);
            }

        };
    }

    @Bean(name = "authenticationUserDetailsService")
    public AuthenticationUserDetailsService getAuthenticationUserDetailsService() {
        return new UserDetailsByNameServiceWrapper(gaeDatastoreUserRegistry);
    }


    // @formatter:off
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        gaeAuthenticationProvider.setPreAuthenticatedUserDetailsService(gaeDatastoreUserRegistry);
        http
            .csrf()
                .disable()
//            .headers()
//                .frameOptions().sameOrigin()
//                .and()
            .authenticationProvider(gaeAuthenticationProvider)
//            .userDetailsService(gaeDatastoreUserRegistry)
            .addFilterBefore(gaeAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
            .authorizeRequests()
                .antMatchers("app/img/**").permitAll()
                .antMatchers("/_ah/login").permitAll()
                .anyRequest().authenticated()
//                .antMatchers("/admin/**").hasAuthority(AuthoritiesConstants.ADMIN)
//                .and()
//            .formLogin()
//                .permitAll()
//                .and()
//            .logout()
//                .deleteCookies(Constants.COOKIE_NAME)
//                .permitAll()
                .and()
            .exceptionHandling()
                .authenticationEntryPoint(googleAuth)
 ;

    }
    // @formatter:on

    /**
     * This allows SpEL support in Spring Data JPA @Query definitions.
     * <p/>
     * See https://spring.io/blog/2014/07/15/spel-support-in-spring-data-jpa-query-definitions
     */
//    @Bean
//    EvaluationContextExtension securityExtension() {
//        return new EvaluationContextExtensionSupport() {
//            @Override
//            public String getExtensionId() {
//                return "security";
//            }
//
//            @Override
//            public SecurityExpressionRoot getRootObject() {
//                return new SecurityExpressionRoot(SecurityContextHolder.getContext().getAuthentication()) {
//                };
//            }
//        };
//    }

}
