package ma.entraide.ash.repository;

import ma.entraide.ash.entity.Etablissement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EtablissementRepo extends JpaRepository<Etablissement, Long> ,
        PagingAndSortingRepository<Etablissement, Long> {
}
