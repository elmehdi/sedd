package ma.env.sedd.Entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;


@Entity @Getter @Setter
public class Intervention implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    private Date date;
    @Enumerated(EnumType.STRING)
    private Type type;
    private String trvEffectuees;
    private java.sql.Time heureDebut;
    private Time duree;
    private String observation;
    private String nomPersonnel;
    private String numBureau;
    @Enumerated(EnumType.STRING)
    private Panne typePanne;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "intervant_id") // nom de lq cle etrangere
    private Intervenant intervenant;

    public Intervenant getIntervenants() {
        return intervenant;
    }

    public void setIntervenants(Intervenant intervenant) {
        this.intervenant = intervenant;
    }

    public Intervention() {
    }
    public Intervention(Date date, Type type, String trv_effectuees,
                        Time heure_debut, Time duree, String observation, String nom_personnel, String num_bureau, Panne type_panne) {
        this.date = date;
        this.type = type;
        this.trvEffectuees = trv_effectuees;
        this.heureDebut = heure_debut;
        this.duree = duree;
        this.observation = observation;
        this.nomPersonnel = nom_personnel;
        this.numBureau = num_bureau;
        this.typePanne = type_panne;
    }

/*    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getTrv_effectuees() {
        return trvEffectuees;
    }

    public void setTrv_effectuees(String trv_effectuees) {
        this.trvEffectuees = trv_effectuees;
    }

    public Time getHeure_debut() {
        return heureDebut;
    }

    public void setHeure_debut(Time heure_debut) {
        this.heureDebut = heure_debut;
    }

    public Time getDuree() {
        return duree;
    }

    public void setDuree(Time duree) {
        this.duree = duree;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public String getNom_personnel() {
        return nomPersonnel;
    }

    public void setNom_personnel(String nom_personnel) {
        this.nomPersonnel = nom_personnel;
    }

    public String getNum_bureau() {
        return numBureau;
    }

    public void setNum_bureau(String num_bureau) {
        this.numBureau = num_bureau;
    }

    public Panne getType_panne() {
        return typePanne;
    }

    public void setType_panne(Panne type_panne) {
        this.typePanne = type_panne;
    }*/
}


