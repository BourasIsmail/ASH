package ma.entraide.ash.service;

import ma.entraide.ash.entity.Demande;
import ma.entraide.ash.repository.DemandeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DemandeService {
    @Autowired
    private DemandeRepo demandeRepo;

    public Page<Demande> getAllDemandes(Pageable pageable) {
        return demandeRepo.findAll(pageable);
    }

    public Demande getDemandeById(Long id) {
        Optional<Demande> demande = demandeRepo.findById(id);
        if(demande.isPresent()) {
            return demande.get();
        }
        else {
            throw new ResourceNotFoundException("Demande not found");
        }
    }

    public Demande addDemande(Demande demande) {
        Date date = new Date();
        demande.setAssociation(demande.getAssociation());
        demande.setEtat("en attente");

        return demandeRepo.save(demande);
    }

    public void deleteDemande(Long id) {
        Optional<Demande> demande = demandeRepo.findById(id);
        if(demande.isPresent()) {
            demandeRepo.delete(demande.get());
        }
        else {
            throw new ResourceNotFoundException("Demande not found");
        }
    }

    public Demande updateDemande(Demande demande) {
        Demande updatedDemande = new Demande();
        updatedDemande.setEtat(demande.getEtat());
        return demandeRepo.save(demande);
    }

    public int nbrDemande(long id) {
        return demandeRepo.countByAssociationId(id);
    }
}
