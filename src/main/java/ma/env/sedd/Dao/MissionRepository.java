package ma.env.sedd.Dao;

import ma.env.sedd.Entities.Mission;
import ma.env.sedd.Entities.Prestataire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MissionRepository extends JpaRepository<Mission, Long> {
    List<Mission> findByPrestataire(Prestataire prestataire);
}
