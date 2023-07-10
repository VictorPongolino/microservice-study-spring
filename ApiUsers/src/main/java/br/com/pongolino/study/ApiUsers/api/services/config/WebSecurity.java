package br.com.pongolino.study.ApiUsers.api.services.config;

import br.com.pongolino.study.ApiUsers.api.data.UserAuthentication;
import br.com.pongolino.study.ApiUsers.api.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurity {

    @Autowired
    private UserRepository userRepository;

    @Bean
    protected UserDetailsService userDetailsService() {
        return emailUsername ->
            userRepository.findByEmail(emailUsername)
                .map(UserAuthentication::new)
                .orElseThrow(() -> new UsernameNotFoundException(emailUsername));
    }

    @Bean
    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        httpSecurity.sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        httpSecurity.authorizeHttpRequests(requests -> {
           requests.requestMatchers("/users/*").permitAll();
           requests.requestMatchers("/h2-console/*").permitAll();
        });

        return httpSecurity.build();
    }
}
