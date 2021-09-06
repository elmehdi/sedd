package ma.env.sedd;

import ma.env.sedd.Dao.*;
import ma.env.sedd.Entities.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootApplication
public class SeddApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(SeddApplication.class, args);
        //Spring IOC
        UserRepository userRepository = context.getBean(UserRepository.class);
        InterventionRepository interventionRepository = context.getBean(InterventionRepository.class);
        PrestataireRepository prestataireRepository = context.getBean(PrestataireRepository.class);
        MissionRepository  missionRepository = context.getBean(MissionRepository.class);
        IntervenantRepository  intervenantRepository = context.getBean(IntervenantRepository.class);
        IntervenantExterneRepository intervenantExterne = context.getBean(IntervenantExterneRepository.class);

        Intervention intervention1 = new Intervention(java.sql.Date.valueOf("2021-08-30"), Type.Curatif, "Cruella", java.sql.Time.valueOf("20:59:10"), java.sql.Time.valueOf("01:20:00"),
                "This an observation", "workr a", "M22", Panne.Materiel);
        Intervention intervention2 = new Intervention(java.sql.Date.valueOf("2021-02-10"), Type.Curatif, "Cruella", java.sql.Time.valueOf("20:59:10"), java.sql.Time.valueOf("01:20:00"),
                "sss", "mehdi", "F14", Panne.Materiel);
        Intervenant mehdi = new Intervenant("Fetouaki", "ElMehdi", "mehdi@gmail.com", "root", Role.Intervenant, 0);
        intervention1.setIntervenants(mehdi);
        intervention2.setIntervenants(mehdi);
        interventionRepository.saveAll(List.of(intervention1, intervention2));

        Intervention i = interventionRepository.findById(1L).get();
        System.out.println(i.getObservation());

        List <Intervention> li = interventionRepository.findByNomPersonnel("mehdi");

        li.forEach(u-> System.out.println(u.getObservation()));
        li.forEach(u-> System.out.println(u.getNomPersonnel()));

        User admin = new User("admin", "root", "root@live.fr", "toor", Role.Admin);

        User a = new User("Najib", "Mootani", "najib@live.fr", "jdsj", Role.Operateur);
        User b = new User("Sami", "Badr", "sami@live.fr", "jdsj", Role.Intervenant);

        userRepository.save(a);
        userRepository.save(b);
        userRepository.save(admin);

        userRepository.findAll().forEach(u-> System.out.println(u.getNom()));
        userRepository.findAll().forEach(u-> System.out.println(u.getRole()));

    }

}
