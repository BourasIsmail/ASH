package ma.entraide.ash.service;

import ma.entraide.ash.entity.Beneficiaire;
import ma.entraide.ash.repository.BeneficiaireRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BeneficiaireService {
    @Autowired
    private BeneficiaireRepo beneficiaireRepo;

    public List<Beneficiaire> getAllBeneficiaires() {
        return beneficiaireRepo.findAll();
    }

    public Page<Beneficiaire> findAllBeneficiaires(Pageable pageable) {
        return beneficiaireRepo.findAll(pageable);
    }

    public Beneficiaire findBeneficiaireById(Long id) {
        Optional<Beneficiaire> beneficiaire = beneficiaireRepo.findById(id);
        if(beneficiaire.isPresent()) {
            return beneficiaire.get();
        }
        else{
            throw new ResourceNotFoundException("Beneficiaire not found");
        }
    }

    public Beneficiaire saveBeneficiaire(Beneficiaire beneficiaire) {
        return beneficiaireRepo.save(beneficiaire);
    }

    public Beneficiaire updateBeneficiaire(Long id,Beneficiaire beneficiaire) {
        Beneficiaire updatedBeneficiaire = findBeneficiaireById(id);
        updatedBeneficiaire.setNom(beneficiaire.getNom());
        updatedBeneficiaire.setDateNaissance(beneficiaire.getDateNaissance());
        updatedBeneficiaire.setSexe(beneficiaire.getSexe());
        updatedBeneficiaire.setTypeHandicap(beneficiaire.getTypeHandicap());
        updatedBeneficiaire.setCertifScolarite(beneficiaire.isCertifScolarite());
        updatedBeneficiaire.setCertifInaptitude(beneficiaire.isCertifInaptitude());
        updatedBeneficiaire.setCertifHandicap(beneficiaire.isCertifHandicap());
        updatedBeneficiaire.setCertifMedical(beneficiaire.isCertifMedical());
        updatedBeneficiaire.setCertifAMO(beneficiaire.isCertifAMO());
        updatedBeneficiaire.setRSU(beneficiaire.isRSU());
        updatedBeneficiaire.setEtablissement(beneficiaire.getEtablissement());
        updatedBeneficiaire.setProgramme(beneficiaire.getProgramme());
        updatedBeneficiaire.setPrestations(beneficiaire.getPrestations());
        return beneficiaireRepo.save(updatedBeneficiaire);
    }

    public void deleteBeneficiaire(Long id) {
        Beneficiaire beneficiaire = findBeneficiaireById(id);
        beneficiaireRepo.delete(beneficiaire);
    }

    public void deleteAllBeneficiaires() {
        beneficiaireRepo.deleteAll();
    }

}
