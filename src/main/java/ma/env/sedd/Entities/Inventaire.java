package ma.env.sedd.Entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.io.Serializable;

@Entity @Getter @Setter
public class Inventaire implements Serializable {
    @Id @GeneratedValue
    private Long id;
    private Long numSerie;
    @Enumerated(EnumType.STRING)
    private Machine type;
    private int panneTotal;

    public Inventaire() {
    }

    public Inventaire(Machine type, int panne_total) {
        this.type = type;
        this.panneTotal = panne_total;
    }
}
