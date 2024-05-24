package ma.entraide.ash.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Association {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "association_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String nomAssociation;

    @Column(nullable = false)
    private String nomPresident;

    @Column(nullable = false)
    private String telPresident;

    @Column(nullable = false)
    @Email
    private String emailPresident;

    @Column(nullable = false)
    private String adresse;

    private int dureeValidite;

    private String dateAssembleGeneral;

    private boolean benefPremFois;

    @Column(nullable = true)
    private double montantPropose;

    @Column(nullable = true)
    private String rib;

    //foreign keys
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "province_id")
    private Province province;


}
