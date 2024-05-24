package ma.entraide.ash.service;

import ma.entraide.ash.entity.Association;
import ma.entraide.ash.entity.Province;
import ma.entraide.ash.repository.AssociationRepo;
import ma.entraide.ash.repository.ProvinceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssociationService {
    @Autowired
    private AssociationRepo associationRepo;
    @Autowired
    private ProvinceRepo provinceRepo;


    public List<Association> getAllAssociations() {
        return associationRepo.findAll();
    }

    public Page<Association> findAllAssociations(Pageable pageable) {
        return associationRepo.findAll(pageable);
    }

    public Association findAssociationById(Long id) {
        Optional<Association> association = associationRepo.findById(id);
        if(association.isPresent()) {
            return association.get();
        }
        else{
            throw new ResourceNotFoundException("Association not found");
        }
    }

    public Association saveAssociation(Association association) {
        try {
            Province province = provinceRepo.findById(association.getProvince().getId())
                    .orElseThrow(()-> new ResourceNotFoundException("Province not found"));
            association.setProvince(province);
            association.setBenefPremFois(true);
            return associationRepo.save(association);
        }catch (Exception e) {
            throw new ResourceNotFoundException(e.getMessage());
        }

    }

    public Association updateAssociation(Long id,Association association) {
        Association updatedAssociation = findAssociationById(id);
        Province province = provinceRepo.findById(association.getProvince().getId())
                .orElseThrow(()-> new ResourceNotFoundException("Province not found"));
        updatedAssociation.setProvince(province);
        updatedAssociation.setNomAssociation(association.getNomAssociation());
        updatedAssociation.setNomPresident(association.getNomPresident());
        updatedAssociation.setTelPresident(association.getTelPresident());
        updatedAssociation.setEmailPresident(association.getEmailPresident());
        updatedAssociation.setAdresse(association.getAdresse());
        updatedAssociation.setDureeValidite(association.getDureeValidite());
        updatedAssociation.setDateAssembleGeneral(association.getDateAssembleGeneral());
        updatedAssociation.setBenefPremFois(association.isBenefPremFois());
        updatedAssociation.setMontantPropose(association.getMontantPropose());
        updatedAssociation.setRib(association.getRib());

        return associationRepo.save(updatedAssociation);
    }

    public String deleteAssociation(Long id) {
        Association association = findAssociationById(id);
        associationRepo.delete(association);
        return "Association removed";
    }
}
