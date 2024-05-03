package ma.entraide.ash.repository;

import ma.entraide.ash.entity.Beneficiaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeneficiaireRepo extends JpaRepository<Beneficiaire, Long> ,
        PagingAndSortingRepository<Beneficiaire, Long> {
}
