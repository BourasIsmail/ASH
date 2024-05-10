package ma.entraide.ash.repository;

import ma.entraide.ash.entity.Association;
import ma.entraide.ash.entity.Etablissement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EtablissementRepo extends JpaRepository<Etablissement, Long> ,
        PagingAndSortingRepository<Etablissement, Long> {

    @Query("SELECT d FROM Etablissement d WHERE d.association.nomAssociation = :nomAssociation")
    List<Etablissement> findByNomAssociation(@Param("nomAssociation") String nomAssociation);


    List<Etablissement> findEtablissementByAssociation(Association association);
}
