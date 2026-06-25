package fr.eni.ludotheque.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())

                .sessionManagement(session ->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS))

                .authorizeHttpRequests(auth -> auth
                        // accès public
                        .requestMatchers(HttpMethod.GET, "/api/jeux")
                        .permitAll()

                        // EMPLOYE et ADMIN
                        .requestMatchers(HttpMethod.POST, "/api/clients")
                        .hasAnyRole("EMPLOYE", "ADMIN")

                        .requestMatchers(HttpMethod.DELETE, "/api/clients/**")
                        .hasAnyRole("EMPLOYE", "ADMIN")

                        .requestMatchers(HttpMethod.PUT, "/api/clients/**")
                        .hasAnyRole("EMPLOYE", "ADMIN")

                        .requestMatchers(HttpMethod.PATCH, "/api/clients/**")
                        .hasAnyRole("EMPLOYE", "ADMIN")

                        .requestMatchers(HttpMethod.GET, "/api/clients/**")
                        .hasAnyRole("EMPLOYE", "ADMIN")
                        // tout le reste nécessite une connexion
                        .anyRequest()
                        .authenticated()
                )
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(
            PasswordEncoder passwordEncoder) {

        UserDetails admin = User.builder()
                .username("chrys")
                .password(passwordEncoder.encode("Chrys123!"))
                .roles("ADMIN")
                .build();

        UserDetails employe = User.builder()
                .username("sisi")
                .password(passwordEncoder.encode("sisi456!"))
                .roles("EMPLOYE")
                .build();

        return new InMemoryUserDetailsManager(
                admin,
                employe
        );
    }
}
