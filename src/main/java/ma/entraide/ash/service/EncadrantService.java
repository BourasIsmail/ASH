package ma.entraide.ash.service;

import ma.entraide.ash.entity.Encadrant;
import ma.entraide.ash.repository.EncadrantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EncadrantService {
    @Autowired
    private EncadrantRepo encadrantRepo;

    public List<Encadrant> getAllEncadrants() {
        return encadrantRepo.findAll();
    }

    public Page<Encadrant> findAllEncadrants(Pageable pageable) {
        return encadrantRepo.findAll(pageable);
    }

    public Encadrant findEncadrantById(Long id) {
        Optional<Encadrant> encadrant = encadrantRepo.findById(id);
        if(encadrant.isPresent()) {
            return encadrant.get();
        }
        else{
            throw new ResourceNotFoundException("Encadrant not found");
        }
    }

    public Encadrant saveEncadrant(Encadrant encadrant) {
        return encadrantRepo.save(encadrant);
    }

    public Encadrant updateEncadrant(Long id,Encadrant encadrant) {
        Encadrant updatedEncadrant = findEncadrantById(id);
        updatedEncadrant.setNomComplet(encadrant.getNomComplet());
        updatedEncadrant.setFonctionExerce(encadrant.getFonctionExerce());
        updatedEncadrant.setDiplome(encadrant.getDiplome());
        updatedEncadrant.setAttestationTravail(encadrant.isAttestationTravail());
        updatedEncadrant.setAnneeExp(encadrant.getAnneeExp());
        updatedEncadrant.setContTravail(encadrant.isContTravail());
        updatedEncadrant.setRemarqueContTravail(encadrant.getRemarqueContTravail());
        updatedEncadrant.setLicensePublic(encadrant.isLicensePublic());
        updatedEncadrant.setRemarqueLicensePublic(encadrant.getRemarqueLicensePublic());
        updatedEncadrant.setNatureContrat(encadrant.getNatureContrat());
        updatedEncadrant.setCNSS(encadrant.isCNSS());
        updatedEncadrant.setEtablissement(encadrant.getEtablissement());

        return encadrantRepo.save(updatedEncadrant);
    }

    public void deleteEncadrant(Long id) {
        Encadrant encadrant = findEncadrantById(id);
        encadrantRepo.delete(encadrant);
    }
}
