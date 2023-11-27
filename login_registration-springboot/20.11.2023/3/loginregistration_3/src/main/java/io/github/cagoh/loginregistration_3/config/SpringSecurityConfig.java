package io.github.cagoh.loginregistration_3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import io.github.cagoh.loginregistration_3.security.JwtAuthenticationEntryPoint;
import io.github.cagoh.loginregistration_3.security.JwtAuthenticationFilter;
import lombok.AllArgsConstructor;

@Configuration
@EnableMethodSecurity
@AllArgsConstructor
public class SpringSecurityConfig {
	private UserDetailsService userDetailsService;

    private JwtAuthenticationEntryPoint authenticationEntryPoint;

    private JwtAuthenticationFilter authenticationFilter;

    @Bean
    static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

//        http.csrf().disable()
//                .authorizeHttpRequests((authorize) -> {
////                    authorize.requestMatchers(HttpMethod.POST, "/api/**").hasRole("ADMIN");
////                    authorize.requestMatchers(HttpMethod.PUT, "/api/**").hasRole("ADMIN");
////                    authorize.requestMatchers(HttpMethod.DELETE, "/api/**").hasRole("ADMIN");
////                    authorize.requestMatchers(HttpMethod.GET, "/api/**").hasAnyRole("ADMIN", "USER");
////                    authorize.requestMatchers(HttpMethod.PATCH, "/api/**").hasAnyRole("ADMIN", "USER");
////                    authorize.requestMatchers(HttpMethod.GET, "/api/**").permitAll();
//                    authorize.requestMatchers("/api/auth/**").permitAll();
//                    authorize.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll();
//                    authorize.anyRequest().authenticated();
//                }).httpBasic(Customizer.withDefaults());
//
//        http.exceptionHandling( exception -> exception
//                .authenticationEntryPoint(authenticationEntryPoint));
//
//        http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
//
//        return http.build();
//    }
    
    	http
    	.csrf((csrf) -> csrf.disable())
    	.authorizeHttpRequests((authorize) -> {
    		authorize
    		.requestMatchers("/api/auth/**").permitAll();
    		authorize
    		.anyRequest().authenticated();
//    		authorize.apply(new JwtConfigurer(jwtTokenProvider));

    	}).httpBasic(Customizer.withDefaults());

    http.exceptionHandling(exception -> exception
    		.authenticationEntryPoint(authenticationEntryPoint));
    
    http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
    return http.build();
}
    	
//    	http
//    	.csrf((csrf) -> csrf.disable())
//    	.authorizeHttpRequests((authorize) -> {
//    		authorize
//    		.anyRequest().permitAll();
//    		authorize.apply(new JwtConfigurer(jwtTokenProvider));

//    	});
//    	.httpBasic(Customizer.withDefaults());

//    http.exceptionHandling(exception -> exception
//    		.authenticationEntryPoint(authenticationEntryPoint));
//    
//    http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
//    return http.build();
//}
    	
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
