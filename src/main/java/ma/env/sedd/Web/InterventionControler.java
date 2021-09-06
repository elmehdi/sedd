package ma.env.sedd.Web;

import ma.env.sedd.Dao.IntervenantRepository;
import ma.env.sedd.Dao.InterventionRepository;
import ma.env.sedd.Dao.InventaireRepository;
import ma.env.sedd.Dao.UserRepository;
import ma.env.sedd.Entities.*;
import ma.env.sedd.Metier.IOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.model.IModel;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
public class InterventionControler {
    @Autowired
    private IOperations iOperations;
    @Autowired
    private InterventionRepository interventionRepository;
    @Autowired
    private InventaireRepository inventaireRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private IntervenantRepository intervenantRepository;
    private Machine m;

    @RequestMapping(value = "/intervention")
    public String getintervention(@ModelAttribute("intervention") Intervention intervention, @ModelAttribute("inventaire") Inventaire inventaire) {
        return "interventionsForm";
    }

    @RequestMapping(value = "/interventionad")
    public String getinterventionad(@ModelAttribute("intervention") Intervention intervention, @ModelAttribute("inventaire") Inventaire inventaire) {
        return "interventionAd";
    }

    @RequestMapping(value = "/mylogs")
    public String mylogs(@RequestParam(name = "motCle", defaultValue = "") String mc, Model model) throws ParseException {
        List<Intervention> interventions;
        if (mc.equals(""))
            interventions = interventionRepository.findAll();
        else {
            interventions = interventionRepository.findByNomPersonnel(mc);
            if (interventions.isEmpty()) {
                interventions.addAll(interventionRepository.findBynumBureau(mc));
            }
        }
        model.addAttribute("interventions", interventions);
        model.addAttribute("motCle", mc);
        return "IntervenantLog";
    }

    @RequestMapping(value = "/saveIntervention", method = RequestMethod.POST)
    public String saveIntervention(@ModelAttribute("intervention") Intervention intervention, @ModelAttribute("inventaire") Inventaire inventaire,
                                   @ModelAttribute("intervenant") Intervenant intervenant,
                                   String typePanne, String nature, String inv,
                                   String nomi, String prenomi, Long ns, String dt) {
        if ((typePanne.equals("Logiciel"))) {
            intervention.setTypePanne(Panne.Logiciel);
        } else {
            intervention.setTypePanne(Panne.Materiel);
        }

        if ((nature.equals("Curatif"))) {
            intervention.setType(Type.Curatif);
        } else if ((nature.equals("Preventif"))) {
            intervention.setType(Type.Preventif);
        } else
            intervention.setType(Type.Evolutif);

        if ((inv.equals("Pc"))) {
            inventaire.setType(Machine.Pc);
            m = Machine.Pc;
        } else if ((inv.equals("Scanner"))) {
            inventaire.setType(Machine.Scanner);
            m = Machine.Scanner;
        } else {
            inventaire.setType(Machine.Imprimante);
            m = Machine.Imprimante;
        }
        Intervenant i = new Intervenant();
        i.setNom(nomi);
        i.setPrenom(prenomi);
        intervention.setIntervenants(i);
        intervention.setDate(java.sql.Date.valueOf(dt));
        interventionRepository.save(intervention);
        if (inventaireRepository.isfound(ns, m))
            iOperations.incrementPanneTotal(ns, m);
        else {
            inventaire.setNumSerie(ns);
            inventaireRepository.save(inventaire);
        }
        return "interventionSaved";
    }

    @RequestMapping(value = "/admin")
    public String admin(@ModelAttribute("intervention") Intervention intervention, @ModelAttribute("inventaire") Inventaire inventaire) {
        return "logs";
    }

    @RequestMapping(value = "/logs")
    public String logs(@RequestParam(name = "motCle", defaultValue = "") String m, Model model) throws ParseException {
        List<Intervention> intervention;
        if (m.equals(""))
            intervention = interventionRepository.findAll();
        else {
            intervention = interventionRepository.findByNomPersonnel(m);
            if (intervention.isEmpty()) {
                intervention.addAll(interventionRepository.findBynumBureau(m));
            }
        }
        model.addAttribute("interventions", intervention);
        model.addAttribute("motCle", m);
        return "logs";
    }
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String usershow(@RequestParam(name = "Nom", defaultValue = "") String name,Model model)
    {
        List<User> users;
        List<Intervenant> intervenants;
        if (name.equals("")) {
            users = userRepository.findAll();
            intervenants = intervenantRepository.findAll();
        }
        else{
            users = userRepository.findByNom(name);
        }
        model.addAttribute("user", users);

        model.addAttribute("Nom", name);


        return "userH";
    }
    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public String userform(@ModelAttribute("u") User u)
    {

        return "userH";
    }

}


