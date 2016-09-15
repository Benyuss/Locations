package hu.benyuss.geohash.webserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
    @Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;

    @Autowired
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .authenticationProvider(this.customAuthenticationProvider);
    }

    @Autowired
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
//		    .authorizeRequests().anyRequest()
//		    .permitAll().and().csrf().disable();
//		        .antMatchers("/index").permitAll()
//				.antMatchers("/choose").permitAll()
//				.antMatchers("/custom-input").hasRole("USER")
//				.antMatchers("/upload").hasRole("USER")
//				.and()
//			.formLogin()
//				.loginPage("/login")
//				.permitAll()
//				.and()
//			.logout()
//				.permitAll();
        
            .authorizeRequests()
                .antMatchers("/api/users/login").permitAll()    // Permit access for all to login REST service
            .anyRequest().authenticated().and()                 // All other requests require authentication
            .httpBasic().and()
            .logout().and()
            .csrf().disable();
    }
}
