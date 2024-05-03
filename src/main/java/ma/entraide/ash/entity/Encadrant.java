package ma.entraide.ash.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Encadrant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "encadrant_id")
    private Long id;

    private String nomComplet;

    private String fonctionExerce;

    private String diplome;

    private boolean attestationTravail;

    //if attestationTravail true =>
    private int anneeExp;

    private boolean contTravail;

    //if contTravail false =>
    private String remarqueContTravail;

    private boolean licensePublic;

    //if licensePublic => false
    private String remarqueLicensePublic;

    private String natureContrat;

    private boolean CNSS;

    //foreign keys
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "etablissement_id")
    private Etablissement etablissement;
}
