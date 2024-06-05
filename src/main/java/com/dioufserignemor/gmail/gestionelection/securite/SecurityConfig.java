//package com.dioufserignemor.gmail.gestionelection.securite;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import static org.springframework.security.config.Customizer.withDefaults;
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig  {
//
//    @Autowired
//    private JwtTokenProvider jwtTokenProvider;
//
//
//@Bean
//   protected  SecurityFilterChain SecurityFilterChain(HttpSecurity http) throws Exception{
//
//        http
//        .authorizeHttpRequests((authz)->authz
//
//            .requestMatchers("/api/login").permitAll()
//
//                .anyRequest().authenticated()
//            )
//            .httpBasic(withDefaults());
//
//        return http.build();
//   }
//
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//            .csrf().disable() // Désactiver la protection CSRF
//            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//            .and()
//            .authorizeRequests()
//                .requestMatchers("/public/**").permitAll()
//                .anyRequest().authenticated()
//            .and()
//            .httpBasic();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}
