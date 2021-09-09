package ma.env.sedd.Web;

import ma.env.sedd.Dao.*;
import ma.env.sedd.Entities.*;
import ma.env.sedd.Metier.IOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.sql.Date;
import java.text.DateFormat;
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
    @Autowired
    private  IntervenantExterneRepository intervenantExterneRepository;
    private Machine m;


    @GetMapping("login")
    public String login()
    {
        return "login";
    }
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
    public String saveIntervention(@ModelAttribute("intervention") Intervention intervention, BindingResult bound,
                                   @ModelAttribute("inventaire") Inventaire inventaire,

                                   String typePanne, String nature, String inv,
                                   String nom, String prenom, Long ns, String dt, String hd) {

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

        Intervenant intervenant = new Intervenant();
        intervenant.setNom(nom);
        intervenant.setPrenom(prenom);
       // if ((intervenantRepository.findByNomAndPrenom(nom, prenom) != null))
        iOperations.incrementNombreIntervention(nom, prenom);
        intervenant.setId(iOperations.getintervenantid(nom, prenom));
     /*   else {
            intervenant.setNombreIntervention(1);
            inventaireRepository.save(inventaire);
        }*/
        intervention.setIntervenants(intervenant);
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        try {
            java.util.Date d = dateFormat.parse(hd);
            intervention.setHeureDebut(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        intervention.setDate(Date.valueOf(dt));
        interventionRepository.save(intervention);
        if (inventaireRepository.isfound(ns, m))
            iOperations.incrementPanneTotal(ns, m);
        else {
            inventaire.setNumSerie(ns);
            inventaire.setPanneTotal(1);
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
    @RequestMapping(value = "/usersform", method = RequestMethod.POST)
    public String userform(String nom, String prenom, String email, String password,
                           String role, String inorex, Model model)
    {
        if(!role.equals("Intervenant"))
        {
            Role r;
            r =  (role.equals("Admin")) ?  Role.Admin : Role.Operateur;
            User u = new User(nom, prenom, email,  password, r);
            userRepository.save(u);
        }
        else if (inorex.equals("Interne"))
        {
            Intervenant ii = new Intervenant(nom, prenom, email,  password, Role.Intervenant, 0);
            intervenantRepository.save(ii);
        }
        else
        {
          /*  IntervenantExterne ie = new IntervenantExterne(u.getNom(), u.getPrenom(),
                    u.getEmail(),  u.getEmail(), u.getRole(), 0);
            intervenantExterneRepository.save(ie);*/
        }
        return "admin";
    }
    @RequestMapping(value = "/delete")
    public String delete(Long id)
    {
        userRepository.deleteById(id);
        return "redirect:/users";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(Long id, Model model)
    {
        User u = userRepository.findById(id).get();
        model.addAttribute("e", u);

        return "redirect:/users";
    }
}


