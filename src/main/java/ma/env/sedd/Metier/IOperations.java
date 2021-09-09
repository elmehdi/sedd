package ma.env.sedd.Metier;

import ma.env.sedd.Entities.Machine;


public interface IOperations {
    public void incrementPanneTotal(Long ns, Machine m);
    public void incrementNombreIntervention(String nom, String prenom);
    public void  changePrestataire(Long id);
    public Long getintervenantid(String nom, String prenom);
}
