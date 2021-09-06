package ma.env.sedd.Dao;

import ma.env.sedd.Entities.Intervenant;
import ma.env.sedd.Entities.Intervention;
import ma.env.sedd.Entities.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.Collection;
import java.util.List;

@Repository
public interface InterventionRepository  extends JpaRepository<Intervention, Long> {
    List<Intervention> findByIntervenant(Intervenant intervenant);
    List<Intervention> findBynumBureau(String numBureau);

    List<Intervention> findByNomPersonnel(String nom_personnel);
    @Query("select i from Intervention i where i.date = :date")
    List<Intervention> findByDate(@Param("date") Date d);
}
