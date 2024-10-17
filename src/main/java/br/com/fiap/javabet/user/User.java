package br.com.fiap.javabet.user;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.List;
import java.util.Map;

@Entity
@Data
@Table(name = "users")
public class User extends DefaultOAuth2User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String avatar;

    public User(){
        super(List.of(), Map.of("email", "anonymous"), "email");
        this.name = "anonymous";
        this.email = "anonymous@email.com";
        this.avatar = "https://avatar.iran.liara.run/public";
    }

    public User(OAuth2User principal){
        super(List.of(new SimpleGrantedAuthority("USER")), principal.getAttributes(), "email");
        this.name = principal.getAttribute("name");
        this.email = principal.getAttribute("email");
        this.avatar = principal.getAttribute("picture");
    }
}
