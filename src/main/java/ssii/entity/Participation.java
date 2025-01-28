package ssii.entity;

import jakarta.persistence.*;
import lombok.NonNull;

@Entity

public class Participation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    private String role;

    @NonNull
    private float pourcentage;

    @ManyToOne
    @JoinColumn(name = "contributeur_id", nullable = false)
    private Personne contributeur;

    @ManyToOne
    @JoinColumn(name = "projet_id", nullable = false)
    private Projet projet;
}
