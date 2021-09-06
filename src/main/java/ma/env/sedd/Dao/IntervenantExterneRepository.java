package ma.env.sedd.Dao;

import ma.env.sedd.Entities.IntervenantExterne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IntervenantExterneRepository extends JpaRepository<IntervenantExterne, Long> {
}
