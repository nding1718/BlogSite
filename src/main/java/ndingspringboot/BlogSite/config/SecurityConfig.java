package ndingspringboot.BlogSite.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * This class is what we used to implement the basic security feature in spring(boot)
 *
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // start methodlevel_security configure
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String KEY = "nan.ding";

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Use BCryptPasswordEncoder as our default password encoder
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return authenticationProvider;
    }

    /**
     *  Self defined configuration
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/css/**", "/js/**", "/fonts/**", "/index").permitAll() // everyone can access
                .antMatchers("/h2-console/**").permitAll() // everyone can access
                .antMatchers("/admins/**").hasRole("ADMIN") // must have corresponding role to access
                .and()
                .formLogin()   //Login based on form
                .loginPage("/login").failureUrl("/login-error") // self-defined login
                .and().rememberMe().key(KEY) // use "Remember me function"
                .and().exceptionHandling().accessDeniedPage("/403");  // handle the exception, if we have access denied, redirect to 403 page
        http.csrf().ignoringAntMatchers("/h2-console/**"); // stop the CSRF protection for h2 console
        http.headers().frameOptions().sameOrigin(); // allow h2 access request from the same source
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
        auth.authenticationProvider(authenticationProvider());
    }
}
