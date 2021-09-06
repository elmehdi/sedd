package ma.env.sedd.Metier;

import ma.env.sedd.Dao.IntervenantRepository;
import ma.env.sedd.Dao.InventaireRepository;
import ma.env.sedd.Entities.Intervenant;
import ma.env.sedd.Entities.Inventaire;
import ma.env.sedd.Entities.Machine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OperationsImpl implements IOperations{
    @Autowired //injecter une implementation de cette interface
    private IntervenantRepository intervenantRepository;
    @Autowired
    private InventaireRepository inventaireRepository;
    @Override
    public void incrementPanneTotal(Long ns, Machine m) {

        inventaireRepository.incrementPanne(ns, m);
    }

    @Override
    public void incrementNombreIntervention(Integer id) {
        Intervenant intervenant = intervenantRepository.findById(id).get();
        intervenant.setNombreIntervention(intervenant.getNombreIntervention() + 1);
        intervenantRepository.save(intervenant);
    }

    @Override
    public void changePrestataire(Long id) {

    }
}
