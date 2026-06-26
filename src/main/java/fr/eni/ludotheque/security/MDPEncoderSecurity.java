package fr.eni.ludotheque.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class MDPEncoderSecurity {
        public static void main(String[] args) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

            System.out.println("Chrys123! -> " + encoder.encode("Chrys123!"));
            System.out.println("sisi456 -> " + encoder.encode("sisi456"));
        }
}
