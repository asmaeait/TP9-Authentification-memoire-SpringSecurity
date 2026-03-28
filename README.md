# SecureDash - Application Spring Security

[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.x-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Spring Security](https://img.shields.io/badge/Spring%20Security-6.5.x-blue.svg)](https://spring.io/projects/spring-security)
[![Thymeleaf](https://img.shields.io/badge/Thymeleaf-3.1.x-yellow.svg)](https://www.thymeleaf.org/)
[![License](https://img.shields.io/badge/License-MIT-red.svg)](LICENSE)

##  Table des matières

- [À propos](#-à-propos)
- [Fonctionnalités](#-fonctionnalités)
- [Technologies utilisées](#-technologies-utilisées)
- [Prérequis](#-prérequis)
- [Installation et exécution](#-installation-et-exécution)
- [Structure du projet](#-structure-du-projet)
- [Comptes de test](#-comptes-de-test)
- [Démonstration](#-démonstration)
- [Architecture de sécurité](#-architecture-de-sécurité)
- [Personnalisation](#-personnalisation)
- [Bonnes pratiques](#-bonnes-pratiques)
- [Auteur](#-auteur)

---

##  À propos

**SecureDash** est une application de démonstration qui illustre l'implémentation de l'authentification et de l'autorisation avec **Spring Security**. Ce projet permet de comprendre les concepts fondamentaux de la sécurité dans une application Spring Boot.

### Objectifs pédagogiques

- Comprendre le modèle d'authentification de Spring Security
- Créer des utilisateurs et rôles statiques en mémoire
- Restreindre l'accès aux pages selon les rôles
- Personnaliser la page de connexion
- Mettre en place une interface utilisateur moderne et responsive

---

##  Fonctionnalités

| Fonctionnalité | Description |
|----------------|-------------|
|  **Authentification** | Formulaire de connexion personnalisé avec validation des identifiants |
|  **Gestion des rôles** | Deux rôles : ADMIN (accès complet) et USER (accès limité) |
|  **Déconnexion** | Système de déconnexion sécurisé avec redirection automatique |
|  **Interface moderne** | Design épuré avec thème sombre, animations et responsive |
|  **Responsive** | Adaptation à tous les écrans (mobile, tablette, desktop) |
|  **Profil utilisateur** | Affichage dynamique des informations de l'utilisateur connecté |
|  **Protection des routes** | Sécurisation des endpoints selon les rôles |

---

##  Technologies utilisées

| Technologie | Version | Description |
|-------------|---------|-------------|
| **Java** | 17+ | Langage de programmation |
| **Spring Boot** | 3.3.x | Framework principal |
| **Spring Security** | 6.5.x | Sécurité et authentification |
| **Spring Web** | 3.3.x | API REST et contrôleurs |
| **Thymeleaf** | 3.1.x | Moteur de templates HTML |
| **Bootstrap** | 5.3.3 | Framework CSS (via WebJars) |
| **Maven** | 3.8+ | Gestionnaire de dépendances |

---

##  Prérequis

Avant de commencer, assurez-vous d'avoir installé :

- **JDK 17** ou supérieur
```bash
java -version
# Output: java version "17.0.x"
```

- **Maven 3.8+** (ou utilisez le wrapper Maven inclus)
```bash
mvn -version
# Output: Apache Maven 3.8.x
```

- **Git** (optionnel, pour cloner le dépôt)

---


###  Exécuter l'application

```bash
mvn spring-boot:run
```

Ou exécutez directement la classe principale dans votre IDE :

```
src/main/java/ma/fstg/security/SpringSecurityDemoApplication.java
```

###  Accéder à l'application

Ouvrez votre navigateur et allez sur : **http://localhost:8080**

> Vous serez automatiquement redirigé vers la page de connexion.

---

##  Structure du projet

<img width="636" height="824" alt="image" src="https://github.com/user-attachments/assets/51babcea-56f6-4d94-803d-9986cf677f7b" />


---

##  Comptes de test

| Rôle | Nom d'utilisateur | Mot de passe | Accès |
|------|-------------------|--------------|-------|
| `ADMIN` | `Asma` | `1234` | Accès complet à toutes les pages |
| `USER` | `user` | `1111` | Accès limité (espace utilisateur uniquement) |

---

##  Démonstration

### Page de connexion
La page de connexion personnalisée avec un design moderne et épuré :

<img width="953" height="473" alt="1" src="https://github.com/user-attachments/assets/f67083ae-b1e3-41e6-90a0-f659489cb890" />

### Tableau de bord principal
Après authentification, l'utilisateur accède au tableau de bord avec les statistiques et les cartes d'accès :

<img width="937" height="467" alt="2" src="https://github.com/user-attachments/assets/ee349462-d61f-4218-a2eb-66b83c1885c7" />



### Espace administrateur
L'interface d'administration avec les privilèges élevés :

<img width="960" height="421" alt="3" src="https://github.com/user-attachments/assets/44104443-0207-455e-859d-9095b8381382" />

---

##  Architecture de sécurité

### Configuration des utilisateurs en mémoire

```java
@Bean
public UserDetailsService userDetailsService() {
    UserDetails admin = User.withUsername("Asma")
            .password("{noop}1234")
            .roles("ADMIN")
            .build();

    UserDetails user = User.withUsername("user")
            .password("{noop}1111")
            .roles("USER")
            .build();

    return new InMemoryUserDetailsManager(admin, user);
}
```

### Règles de sécurité

| Route | Rôle requis |
|-------|-------------|
| `/` | Authentification requise |
| `/user/**` | `USER` ou `ADMIN` |
| `/admin/**` | `ADMIN` uniquement |
| `/login` | Public (`permitAll`) |
| `/css/**, /js/**` | Public |

---

##  Personnalisation

### Modifier les utilisateurs

Dans `SecurityConfig.java`, modifiez la méthode `userDetailsService()` :

```java
UserDetails monUtilisateur = User.withUsername("monNom")
        .password("{noop}monMotDePasse")
        .roles("MON_ROLE")
        .build();
```

### Modifier les styles

Les styles sont dans `src/main/resources/static/css/custom.css`. Vous pouvez modifier :

- **Les couleurs** : variables CSS dans `:root`
- **Les polices** : changer la famille dans `@import`
- **Les animations** : modifier `@keyframes`

### Ajouter une nouvelle page

1. Créez un fichier HTML dans `templates/`
2. Ajoutez une méthode dans `HomeController.java` :

```java
@GetMapping("/ma-page")
public String maPage() {
    return "ma-page";
}
```

3. Ajoutez la règle de sécurité dans `SecurityConfig.java`

---

##  Bonnes pratiques

| Pratique | Statut | Remarque |
|----------|--------|----------|
| Encodage des mots de passe |  À améliorer | Actuellement `{noop}` (développement uniquement) |
| Séparation des responsabilités |  OK | Config, Controllers, Templates séparés |
| Gestion des rôles |  OK | Hiérarchie des rôles fonctionnelle |
| Protection CSRF |  OK | Activée par défaut |
| Session management | OK | Gestion automatique par Spring |
| Interface responsive | OK | Adaptation mobile et desktop |


---

##  Contribution

Les contributions sont les bienvenues ! Pour contribuer :

1. Forkez le projet
2. Créez votre branche (`git checkout -b feature/AmazingFeature`)
3. Commitez vos changements (`git commit -m 'Add some AmazingFeature'`)
4. Pushez vers la branche (`git push origin feature/AmazingFeature`)
5. Ouvrez une Pull Request

---

##  Licence

Ce projet est sous licence MIT - voir le fichier [LICENSE](LICENSE) pour plus de détails.

---

