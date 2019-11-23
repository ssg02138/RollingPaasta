package org.paasta.rollingadmin;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import de.codecentric.boot.admin.server.config.AdminServerProperties;

@Configuration
public class SecuritySecureConfig extends WebSecurityConfigurerAdapter {
    private final AdminServerProperties adminServer;

    public SecuritySecureConfig(AdminServerProperties adminServer) {
        this.adminServer = adminServer;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
        successHandler.setTargetUrlParameter("redirectTo");
        successHandler.setDefaultTargetUrl(this.adminServer.getContextPath()+("/"));

        http.authorizeRequests()
            .antMatchers(this.adminServer.getContextPath()+"/assets/**").permitAll() // <1>
            .antMatchers(this.adminServer.getContextPath()+"/login").permitAll()
            .anyRequest().authenticated() // <2>
            .and()
        .formLogin().loginPage(this.adminServer.getContextPath()+"/login").successHandler(successHandler).and() // <3>
        .logout().logoutUrl(this.adminServer.getContextPath()+("/logout")).and()
        .httpBasic().and() // <4>
        .csrf()
            .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()) // <5>
            .ignoringAntMatchers(
                this.adminServer.getContextPath()+("/instances"), // <6>
                this.adminServer.getContextPath()+("/actuator/**") // <7>
            );
        // @formatter:on
    }
}
// end::configuration-spring-security[]
