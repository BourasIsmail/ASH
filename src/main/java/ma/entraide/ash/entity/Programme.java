package ma.entraide.ash.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Programme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "programme_id")
    private Long id;

    private String option;


}
