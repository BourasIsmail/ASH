package ma.entraide.ash.repository;

import ma.entraide.ash.entity.Association;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssociationRepo extends JpaRepository<Association, Long> ,
        PagingAndSortingRepository<Association, Long> {

}
