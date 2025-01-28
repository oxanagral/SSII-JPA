package ssii.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ssii.entity.Personne;
import ssii.entity.Projet;
import ssii.dao.ParticipationService;

@SpringBootTest
class ParticipationServiceTest {

    @Autowired
    private ParticipationService participationService;

    @Autowired
    private PersonneRepository personneRepository;

    @Autowired
    private ParticipationRepository participationRepository;

    @Autowired
    private ProjetRepository projetRepository;

    private Personne p1;
    private Projet projet1, projet2, projet3, projet4;

    @BeforeEach
    void setUp() {
        // On supprime d'abord les participations et projets
        participationRepository.deleteAll();
        personneRepository.deleteAll();
        projetRepository.deleteAll();

        // On crée et sauvegarde une Personne
        p1 = new Personne("Kadi");
        p1.setPrenom("mima");
        p1.setPoste("Ingénieure en bio-médicale");
        p1 = personneRepository.save(p1);

        // On crée quatre Projets en mémoire
        projet1 = new Projet();
        projet1.setCode(302);
        projet1.setNom("Projet A");

        projet2 = new Projet();
        projet2.setCode(303);
        projet2.setNom("Projet B");

        projet3 = new Projet();
        projet3.setCode(304);
        projet3.setNom("Projet C");

        // On enregistre ds la base
        projet1 = projetRepository.save(projet1);
        projet2 = projetRepository.save(projet2);
        projet3 = projetRepository.save(projet3);

    }

    @Test
    void testRegle1PasDeDoubleParticipation() {
        // 1ère participation OK
        participationService.enregistrerParticipation(
                p1.getMatricule(), projet1.getCode(), "Développeuse", 50f);

        // 2ème fois => Doit lever IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> {
            participationService.enregistrerParticipation(
                    p1.getMatricule(), projet1.getCode(), "Test", 20f);
        });
    }

    @Test
    void testRegle2OccupationMax100pourcent() {
        // 70% + 30% => 100% => OK
        participationService.enregistrerParticipation(
                p1.getMatricule(), projet1.getCode(), "Développeuse", 70f);
        participationService.enregistrerParticipation(
                p1.getMatricule(), projet2.getCode(), "Développeuse", 30f);

        // 10% en plus => dépasse 100% => exception
        assertThrows(IllegalArgumentException.class, () -> {
            participationService.enregistrerParticipation(
                    p1.getMatricule(), projet3.getCode(), "Développeuse", 10f);
        });
    }

    @Test
    void testRegle3MaxTroisProjetsActifs() {
        // 3 projets => OK
        participationService.enregistrerParticipation(
                p1.getMatricule(), projet1.getCode(), "Développeuse", 30f);
        participationService.enregistrerParticipation(
                p1.getMatricule(), projet2.getCode(), "Développeuse", 30f);
        participationService.enregistrerParticipation(
                p1.getMatricule(), projet3.getCode(), "Développeuse", 30f);

        // 4e => exception
        assertThrows(IllegalArgumentException.class, () -> {
            participationService.enregistrerParticipation(
                    p1.getMatricule(), projet4.getCode(), "Développeuse", 10f);
        });
    }
}