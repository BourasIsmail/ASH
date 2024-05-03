package ma.entraide.ash.service;

import ma.entraide.ash.entity.Etablissement;
import ma.entraide.ash.repository.EtablissementRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EtablissementService {
    @Autowired
    private EtablissementRepo etablissementRepo;

    public List<Etablissement> getAllEtablissement() {
        return etablissementRepo.findAll();
    }

    public Page<Etablissement> findAllEtablissement(Pageable pageable) {
        return etablissementRepo.findAll(pageable);
    }

    public Etablissement getEtablissementById(Long id) {
        Optional<Etablissement> etabOpt = etablissementRepo.findById(id);
        if (etabOpt.isPresent()) {
            return etabOpt.get();
        }
        else {
            throw new ResourceNotFoundException("Etablissement introuvable");
        }
    }

    public Etablissement saveEtablissement(Etablissement etab) {
        return etablissementRepo.save(etab);
    }
}
