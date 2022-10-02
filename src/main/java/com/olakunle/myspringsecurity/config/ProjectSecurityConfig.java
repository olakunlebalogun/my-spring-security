package com.olakunle.myspringsecurity.config;

import com.olakunle.myspringsecurity.security.ApplicationUserPermission;
import com.olakunle.myspringsecurity.security.ApplicationUserRole;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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
                .csrf().disable() // TODO: just try out the todo
                .authorizeHttpRequests()
                .antMatchers("/", "index", "/css/**", "/js/**").permitAll()
                .antMatchers("/student/api/**").hasRole(ApplicationUserRole.STUDENT.name())
                .antMatchers(HttpMethod.DELETE, "/management/student/api/**").hasAuthority(ApplicationUserPermission.COURSE_WRITE.getPermission())
                .antMatchers(HttpMethod.POST, "/management/student/api/**").hasAuthority(ApplicationUserPermission.COURSE_WRITE.getPermission())
                .antMatchers(HttpMethod.PUT, "/management/student/api/**").hasAuthority(ApplicationUserPermission.COURSE_WRITE.getPermission())
                .antMatchers(HttpMethod.GET, "/management/student/api/**").hasAnyRole(ApplicationUserRole.ADMIN.name(), ApplicationUserRole.TRAINEE.name())
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
//                .roles(ApplicationUserRole.STUDENT.name())
                .authorities(ApplicationUserRole.STUDENT.getGrantedAuthorities())
                .build();
        UserDetails adminOlakunle = User.builder()
                .username("Olakunle")
                .password(passwordEncoder.encode("olaoluwa"))
//                .roles(ApplicationUserRole.ADMIN.name())
                .authorities(ApplicationUserRole.ADMIN.getGrantedAuthorities())
                .build();

        UserDetails traineeBalo = User.builder()
                .username("Olanrewaju")
                .password(passwordEncoder.encode("larry"))
//                .roles(ApplicationUserRole.TRAINEE.name())
                .authorities(ApplicationUserRole.TRAINEE.getGrantedAuthorities())
                .build();
        return new InMemoryUserDetailsManager(
                studentNiyi,
                adminOlakunle,
                traineeBalo
        );
    }


}
