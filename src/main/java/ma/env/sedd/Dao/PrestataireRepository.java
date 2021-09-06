package ma.env.sedd.Dao;

import ma.env.sedd.Entities.Prestataire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.function.Predicate;

@Repository
public interface PrestataireRepository extends JpaRepository<Prestataire, Integer> {
}
