package ma.fstg.security.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Contrôleur principal de l'application
 * Gère les routes principales et la navigation
 *
 * @author Asma
 * @version 2.0
 */
@Controller
public class HomeController {

    /**
     * Page d'accueil après authentification
     * Affiche le tableau de bord principal
     *
     * @return Le nom de la vue Thymeleaf "home"
     */
    @GetMapping("/")
    public String home() {
        return "home";
    }

    /**
     * Page de connexion personnalisée
     * Accessible sans authentification (permitAll)
     *
     * @return Le nom de la vue Thymeleaf "login"
     */
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    /**
     * Dashboard utilisateur
     * Accessible aux rôles USER et ADMIN
     *
     * @return Le nom de la vue Thymeleaf "user-dashboard"
     */
    @GetMapping("/user/dashboard")
    public String userDashboard() {
        return "user-dashboard";
    }

    /**
     * Dashboard administrateur
     * Accessible uniquement au rôle ADMIN
     *
     * @return Le nom de la vue Thymeleaf "admin-dashboard"
     */
    @GetMapping("/admin/dashboard")
    public String adminDashboard() {
        return "admin-dashboard";
    }
}