package ma.entraide.ash.repository;

import ma.entraide.ash.entity.Encadrant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EncadrantRepo extends JpaRepository<Encadrant, Long> ,
        PagingAndSortingRepository<Encadrant, Long> {
}
