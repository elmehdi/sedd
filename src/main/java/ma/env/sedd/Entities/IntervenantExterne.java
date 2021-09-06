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
    }

    public void setMissions(Collection<Mission> missions) {
        this.missions = missions;
    }
}
