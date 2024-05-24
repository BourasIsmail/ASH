package ma.entraide.ash.repository;

import ma.entraide.ash.entity.Demande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DemandeRepo extends JpaRepository<Demande, Long>,
        PagingAndSortingRepository<Demande, Long> {
    @Query("SELECT count(d) FROM Demande d where d.association.id = :id")
    int countByAssociationId(@Param("id") Long id);
}
