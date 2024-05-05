package ma.entraide.ash.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Etablissement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "etablissement_id")
    private Long id;

    private String nomEtablissement;

    private String nomDirecteur;

    private String telDirecteur;

    private String email;

    private String milieu;

    private boolean dispoConditionStruct;

    private boolean dispoConditionFonc;

    //if dispoConditionFonc is false
    private String remarque;

    private String situation;

    @Column(unique = true)
    private String codeAutorisation;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "association_id")
    private Association association;

    @OneToMany(mappedBy = "etablissement", cascade = CascadeType.DETACH)
    private List<Beneficiaire> beneficiaires = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "programme_id")
    private Programme programme;
}
