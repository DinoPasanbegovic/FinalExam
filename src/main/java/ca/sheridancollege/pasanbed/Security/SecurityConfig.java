package ca.sheridancollege.pasanbed.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private LoginAccessDeniedHandler accessdeniedhandler;
	
	
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
		.antMatchers("/user/**").hasRole("USER")
		.antMatchers("/admin/**").hasRole("ADMIN")
		.antMatchers(HttpMethod.POST, "/register").permitAll()
		.antMatchers(
				"/", // access to all users
				"/js/**",
				"/css/**",
				"/images/**",
				"/**")
		.permitAll() // access to all users
				.anyRequest().authenticated()
				.and()
				.formLogin()
					.loginPage("/login")
					.permitAll()
				.and()
				.logout()
					.invalidateHttpSession(true)
					.clearAuthentication(true)
					.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
					.logoutSuccessUrl("/")
					.permitAll()
				.and()
					.exceptionHandling()
					.accessDeniedHandler(accessdeniedhandler);
	}
	@Autowired 
	UserDetailedServiceimple userDetialsService;
	
	@Bean
	public BCryptPasswordEncoder passwordEncroder() {
		
		return new BCryptPasswordEncoder();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)	throws Exception {
	
	auth.userDetailsService(userDetialsService).
	passwordEncoder(passwordEncroder());
	}
}