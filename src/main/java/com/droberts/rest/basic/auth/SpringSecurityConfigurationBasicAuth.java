package com.droberts.rest.basic.auth;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.bind.annotation.CrossOrigin;

//@Configuration
//@EnableWebSecurity
//public class SpringSecurityConfigurationBasicAuth extends WebSecurityConfigurerAdapter {
//
////	@Override
////	protected void configure(HttpSecurity http) throws Exception {
////
////		http.authorizeRequests((requests) -> requests.anyRequest().authenticated());
////		http.antMatchers(HttpMethod.OPTIONS, "/**").permitAll();
////		
////		
////		//disabling csrf since going to use JWTs instead
////		http.csrf().disable(); 
////		//disabling form login by commenting it out
////		//http.formLogin();
////		
////		http.httpBasic();
////	}
//	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http
//		.csrf().disable()	
//		.authorizeRequests()
//		.antMatchers(HttpMethod.OPTIONS,"/**").permitAll() //accept options requests freely, 
//				.anyRequest().authenticated() //authenticates everyone else
//				.and()
//			//.formLogin().and()
//			.httpBasic();
//	}
//}


@Configuration
@EnableWebSecurity
//@CrossOrigin(origins="http://localhost:4200") //prob don't need, take out if errors
public class SpringSecurityConfigurationBasicAuth extends WebSecurityConfigurerAdapter {
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http
//        .csrf().disable()   
//        .authorizeRequests()
//        .antMatchers(HttpMethod.OPTIONS,"/**").permitAll()
//                .anyRequest().authenticated()
//                .and()
//            //.formLogin().and()
//            .httpBasic();
        
        http.
        csrf().disable()   
        .authorizeRequests()
        .antMatchers(HttpMethod.OPTIONS,"/**").permitAll()
                .anyRequest().authenticated()
                .and()
            //.formLogin().and()
            .httpBasic();
        System.out.println("actually used the stupid override");
    }

    
}