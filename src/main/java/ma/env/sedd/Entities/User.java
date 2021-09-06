package ma.env.sedd.Entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)

@Getter @Setter @ToString
public class User implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    @Column(length = 40)
    private String nom;
    @Column(length = 40)
    private String prenom;
    @Column(length = 60)
    private String email;
    @Column(length = 40)
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;

    public User() {
    }

    public User(String nom, String prenom, String email, String password, Role role) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
