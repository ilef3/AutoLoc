package tn.esprit.autoloc.domain;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Agence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAgence;

    private String nom;
    private String ville;
    private String adresse;
    private String telephone;

    @com.fasterxml.jackson.annotation.JsonIgnore
    @OneToMany(mappedBy = "agence")
    private List<Vehicule> vehicules;

    @com.fasterxml.jackson.annotation.JsonIgnore
    @OneToMany(mappedBy = "agence")
    private List<Employe> employes;
}