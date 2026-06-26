/**package fr.eni.ludotheque.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        System.out.println("Connexion de : " + username);

        if ("chrys".equals(username)) {
            return User.builder()
                    .username("chrys")
                    .password("{bcrypt}$2a$10$...")
                    .roles("ADMIN")
                    .build();
        }

        if ("sisi".equals(username)) {
            return User.builder()
                    .username("sisi")
                    .password("{bcrypt}$2a$10$...")
                    .roles("USER")
                    .build();
        }

        throw new UsernameNotFoundException(username);
    }
}*/