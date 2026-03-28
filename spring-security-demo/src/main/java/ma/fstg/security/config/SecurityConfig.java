package ma.fstg.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configuration de sécurité de l'application
 * Définit les utilisateurs en mémoire et les règles d'accès aux routes
 *
 * @author Asma
 * @version 3.0
 */
@Configuration
public class SecurityConfig {

    /**
     * Crée un service d'utilisateurs en mémoire
     * Définit 2 utilisateurs avec leurs rôles
     */
    @Bean
    public UserDetailsService userDetailsService() {
        // Utilisateur administrateur - Asma Ait
        UserDetails admin = User.withUsername("Asma")
                .password("{noop}1234")
                .roles("ADMIN")
                .build();

        // Utilisateur standard
        UserDetails user = User.withUsername("user")
                .password("{noop}1111")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(admin, user);
    }

    /**
     * Configuration des règles de sécurité HTTP
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        // Routes ADMIN uniquement
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        // Routes accessibles aux USER et ADMIN
                        .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")
                        // Profil accessible à tous les utilisateurs authentifiés
                        .requestMatchers("/profile").authenticated()
                        // Routes publiques (CSS, images)
                        .requestMatchers("/css/**", "/js/**", "/webjars/**").permitAll()
                        // Toute autre route nécessite une authentification
                        .anyRequest().authenticated()
                )
                // Configuration du formulaire de connexion personnalisé
                .formLogin(form -> form
                        .loginPage("/login")
                        .permitAll()
                )
                // Configuration de la déconnexion
                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                );

        return http.build();
    }
}