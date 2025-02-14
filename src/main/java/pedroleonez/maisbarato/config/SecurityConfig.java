package pedroleonez.maisbarato.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import pedroleonez.maisbarato.service.CustomUserDetailsService;

@Configuration
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;

    public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/register").permitAll()  // Permitir acesso ao login e registro
                        .anyRequest().authenticated()  // Todas as outras requisições precisam de autenticação
                )
                .formLogin(form -> form
                        .loginPage("/login").permitAll()  // Página de login customizada
                        .defaultSuccessUrl("/home", true) // Redireciona após login bem-sucedido
                )
                .logout(logout -> logout
                        .logoutUrl("/logout").permitAll()  // Rota para logout
                        .logoutSuccessUrl("/login?logout") // Redireciona após logout
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

