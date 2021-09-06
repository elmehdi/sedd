package ma.env.sedd.Metier;

import ma.env.sedd.Entities.Machine;

public interface IOperations {
    public void incrementPanneTotal(Long ns, Machine m);
    public void incrementNombreIntervention(Integer id);
    public void  changePrestataire(Long id);

}
