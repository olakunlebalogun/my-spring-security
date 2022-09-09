package com.olakunle.myspringsecurity.config;

import com.olakunle.myspringsecurity.security.ApplicationUserRole;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
//@EnableWebSecurity
public class ProjectSecurityConfig extends WebSecurityConfigurerAdapter{

    //
    private final PasswordEncoder passwordEncoder;

    public ProjectSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests()
                .antMatchers("/", "index", "/css/**", "/js/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails studentNiyi = User.builder()
                .username("Olaniyi")
                .password(passwordEncoder.encode("niyiola"))
                .roles(ApplicationUserRole.STUDENT.name())
                .build();
        UserDetails adminOlakunle = User.builder()
                .username("Olakunle")
                .password(passwordEncoder.encode("olaoluwa"))
                .roles(ApplicationUserRole.ADMIN.name())
                .build();
        return new InMemoryUserDetailsManager(
                studentNiyi,
                adminOlakunle
        );
    }


}
