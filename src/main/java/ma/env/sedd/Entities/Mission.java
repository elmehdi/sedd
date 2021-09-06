package ma.env.sedd.Entities;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
public class Mission implements Serializable {
    @Id @GeneratedValue
    private Long id;
    @Basic
    private Date debut;
    @Basic
    private Date fin;
    @Column(length = 30)
    private String intitule;
    @ManyToOne
    private IntervenantExterne ie;
    @OneToOne
    private Prestataire prestataire;

    public Mission() {
    }

    public Mission(Date debut, Date fin, String intitule) {
        this.debut = debut;
        this.fin = fin;
        this.intitule = intitule;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDebut() {
        return debut;
    }

    public void setDebut(Date debut) {
        this.debut = debut;
    }

    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public IntervenantExterne getIe() {
        return ie;
    }

    public void setIe(IntervenantExterne ie) {
        this.ie = ie;
    }

    public Prestataire getPrestataire() {
        return prestataire;
    }

    public void setPrestataire(Prestataire prestataire) {
        this.prestataire = prestataire;
    }
}
