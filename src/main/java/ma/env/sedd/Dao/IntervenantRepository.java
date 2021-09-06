package ma.env.sedd.Dao;

import ma.env.sedd.Entities.Intervenant;
import ma.env.sedd.Entities.Intervention;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IntervenantRepository extends JpaRepository<Intervenant, Integer> {
    @Query("select i from Intervenant i where i.nom = :x")
    Intervenant findByIntervenantName(@Param("x") String intervenantname);
    default void addInterventionToIntervenant(String intervenantName, Long interventionId)
    {
        Intervention in;
        InterventionRepository inr = null;
        in = inr.findById(interventionId).get();

    }
}
