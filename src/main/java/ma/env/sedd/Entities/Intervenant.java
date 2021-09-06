package ma.env.sedd.Entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter @Setter
public class Intervenant extends User implements Serializable {
    private int nombreIntervention;
    @OneToMany(mappedBy = "intervenant")
    private Collection<Intervention> interventions = new ArrayList<>();

    public Intervenant() {
    }
    public Intervenant(String nom, String prenom, String email, String password, Role role, int nombreIntervention) {
        super(nom, prenom, email, password, role);
        this.nombreIntervention = nombreIntervention;
    }

    public void addIntervention(Intervention intervention) {
        this.interventions.add(intervention);
    }
}
