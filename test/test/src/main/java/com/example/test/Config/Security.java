package com.example.test.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class Security {

    private  final CustomUserDetailsService customUserDetailsService;
    @Autowired
    public Security(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }
//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails admin = User.builder()
//                .username("Admin")
//                .password(passwordEncoder().encode("1234"))
//                .roles("ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(admin);
//    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
//
    @Bean
    public AuthenticationManager authenticationManager
            (AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .cors(Customizer.withDefaults()) // Tích hợp CORS với Spring Security
                .authorizeRequests(authorizeRequests -> authorizeRequests
//                                .requestMatchers("/admin/**").hasRole("ADMIN") // Chỉ ADMIN được phép truy cập
                                .anyRequest().permitAll()
                )
//                .formLogin(form -> form
//                        .loginPage("/admin/user")
////                        .permitAll()
//
//                )

                .httpBasic(Customizer.withDefaults()) // Nếu bạn muốn sử dụng HTTP Basic Authentication, nếu không có thể bỏ qua
                .csrf(csrf -> csrf.disable()) // Tắt CSRF vì đây là API REST
                .userDetailsService(customUserDetailsService); // Sử dụng customUserDetailsService để load thông tin người dùng từ DB

        return http.build();
    }
    @Bean
    public CorsFilter corsFilter() {
        return new CorsFilter(corsConfigurationSource());
    }

    private UrlBasedCorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("http://localhost:3001"); // Địa chỉ Frontend
        config.addAllowedMethod("*"); // Cho phép tất cả các phương thức HTTP
        config.addAllowedHeader("*"); // Cho phép tất cả các header
        config.setAllowCredentials(true); // Cho phép gửi thông tin xác thực (cookies, token)

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

}
