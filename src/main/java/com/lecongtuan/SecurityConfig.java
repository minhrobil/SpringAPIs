package com.lecongtuan;

import org.springframework.context.annotation.Configuration;

@Configuration
//@EnableWebSecurity
public class SecurityConfig {

//	@Autowired
//	UserLoginServiceImpl userLoginService;
//	
//	@Bean
//	BCryptPasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder(12);
//	}
//	
//	@Bean
//	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//		http.csrf().disable()
//			.authorizeRequests()
//			.antMatchers("/**")
//			.hasRole("ADMIN")
//			.anyRequest()
//			.authenticated()
//			.and()
//			.formLogin()
//				.loginPage("/login")
//				.defaultSuccessUrl("/", true)
//				.failureUrl("/login?error=error")
//				.permitAll()
//			.and()
//			.logout().permitAll()
//			.and()
//			.exceptionHandling()
//			.accessDeniedPage("/login?error=deny");
//		
//		http.authenticationProvider(authenticationProvider());
//		return http.build();
//	}
//	
//	@Bean
//	WebSecurityCustomizer webSecurityCustomizer() {
//		return web -> web.ignoring().antMatchers("/css/**", "/img/**", "/js/**");
//	}
//	
//	@Bean
//	AuthenticationManager authenticationManager(
//			AuthenticationConfiguration auth) throws Exception {
//		return auth.getAuthenticationManager();
//	}
//	
//	@Bean
//	DaoAuthenticationProvider authenticationProvider() {
//		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//		
//		authProvider.setUserDetailsService(userLoginService);
//		authProvider.setPasswordEncoder(passwordEncoder());
//		
//		return authProvider;
//	}
	
}
