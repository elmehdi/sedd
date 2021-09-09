package ma.env.sedd.Entities;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Getter
public class IntervenantExterne extends Intervenant implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    @OneToMany(mappedBy = "ie")
    private Collection<Mission> missions;

    public IntervenantExterne() {
        super();
    }

    public IntervenantExterne(String nom, String prenom, String email, String password, Role role, int nombreIntervention) {
        super(nom, prenom, email, password, role, nombreIntervention);
    }

    public void setMissions(Collection<Mission> missions) {
        this.missions = missions;
    }
}
