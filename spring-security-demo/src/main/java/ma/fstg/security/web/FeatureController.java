package ma.fstg.security.web;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Contrôleur pour les fonctionnalités supplémentaires
 * Affiche des informations dynamiques sur l'utilisateur connecté
 */
@Controller
public class FeatureController {

    /**
     * Affiche le profil de l'utilisateur connecté
     * Utilise le contexte de sécurité Spring pour récupérer les informations
     */
    @GetMapping("/profile")
    public String showProfile(Model model) {
        // Récupération de l'authentification courante
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        // Ajout des informations au modèle
        model.addAttribute("username", auth.getName());
        model.addAttribute("roles", auth.getAuthorities());
        model.addAttribute("isAuthenticated", auth.isAuthenticated());
        model.addAttribute("currentTime", LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));

        return "profile";
    }

    /**
     * Page de statistiques (simulées)
     * Accessible uniquement aux administrateurs via la configuration
     */
    @GetMapping("/admin/stats")
    public String adminStats(Model model) {
        // Données de statistiques simulées
        model.addAttribute("totalUsers", 2);
        model.addAttribute("activeSessions", 1);
        model.addAttribute("lastLogin", "2024-03-28 10:30:00");
        model.addAttribute("systemUptime", "2 jours 5 heures");

        return "admin-stats";
    }
}