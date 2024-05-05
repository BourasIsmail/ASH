package ma.entraide.ash.service;

import ma.entraide.ash.entity.Prestation;
import ma.entraide.ash.repository.PrestationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrestationService {
    @Autowired
    private PrestationRepo prestationRepo;

    public List<Prestation> getAllPrestations() {
        return prestationRepo.findAll();
    }

    public Page<Prestation> findAllPrestations(Pageable pageable) {
        return prestationRepo.findAll(pageable);
    }

    public Prestation findPrestationById(Long id) {
        Optional<Prestation> prestation = prestationRepo.findById(id);
        if(prestation.isPresent()) {
            return prestation.get();
        }
        else{
            throw new ResourceNotFoundException("Prestation not found");
        }
    }

    public Prestation savePrestation(Prestation prestation) {
        return prestationRepo.save(prestation);
    }

    public Prestation updatePrestation(Long id,Prestation prestation) {
        Prestation updatedPrestation = findPrestationById(id);
        updatedPrestation.setOption(prestation.getOption());
        return prestationRepo.save(updatedPrestation);
    }

    public void deletePrestation(Long id) {
        Prestation prestation = findPrestationById(id);
        prestationRepo.delete(prestation);
    }
}
