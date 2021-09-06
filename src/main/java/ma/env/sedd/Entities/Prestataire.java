package ma.env.sedd.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Entity
public class Prestataire implements Serializable {
    @Id
    @GeneratedValue
    private Integer num_registre;
    private String adresse;
    private String tel;
    @OneToOne(mappedBy = "prestataire")
    private Mission mission;

    public Prestataire() {
    }

    public Prestataire(Integer num_registre, String adresse, String tel) {
        this.num_registre = num_registre;
        this.adresse = adresse;
        this.tel = tel;
    }

    public Integer getNum_registre() {
        return num_registre;
    }

    public void setNum_registre(Integer num_registre) {
        this.num_registre = num_registre;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Mission getMission() {
        return mission;
    }

    public void setMission(Mission mission) {
        this.mission = mission;
    }
}
