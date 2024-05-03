package ma.entraide.ash.repository;

import ma.entraide.ash.entity.Prestation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrestationRepo extends JpaRepository<Prestation, Long> ,
        PagingAndSortingRepository<Prestation, Long> {
}
