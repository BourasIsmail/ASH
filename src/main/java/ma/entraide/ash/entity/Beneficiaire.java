package ma.entraide.ash.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Beneficiaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "benef_id")
    private Long id;

    private String nom;

    private String dateNaissance;

    private String sexe;

    private List<String> typeHandicap = new ArrayList<String>();

    private boolean certifScolarite;

    private boolean certifInaptitude;

    private boolean certifHandicap;

    private boolean certifMedical;

    private boolean certifAMO;

    private boolean RSU;

    //foreign keys
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "etablissement_id")
    private Etablissement etablissement;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "programme_id")
    private Programme programme;

    @ManyToMany(cascade = CascadeType.DETACH)
    @JoinTable(name = "beneficiaire_prestation", // Name of the join table
            joinColumns = @JoinColumn(name = "beneficiaire_id"),
            inverseJoinColumns = @JoinColumn(name = "prestation_id"))
    private List<Prestation> prestations = new ArrayList<>();

}
