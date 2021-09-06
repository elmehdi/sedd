package ma.env.sedd.Dao;

import ma.env.sedd.Entities.Inventaire;
import ma.env.sedd.Entities.Machine;
import ma.env.sedd.Entities.Mission;
import ma.env.sedd.Entities.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface InventaireRepository extends JpaRepository<Inventaire, Long> {
    @Transactional
    @Modifying
    @Query("update Inventaire inv set inv.panneTotal = inv.panneTotal+1 where inv.numSerie=:x and inv.type=:y")
    public void incrementPanne(@Param("x") Long ns,@Param("y") Machine m);
    @Query(" select (count(inv) > 0) from Inventaire inv where inv.numSerie = :c and inv.type=:m")
    public boolean isfound(@Param("c") Long ns, @Param("m") Machine m);
}
