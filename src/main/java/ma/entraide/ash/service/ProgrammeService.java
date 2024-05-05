package ma.entraide.ash.service;

import ma.entraide.ash.entity.Programme;
import ma.entraide.ash.repository.ProgrammeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProgrammeService {
    @Autowired
    private ProgrammeRepo programmeRepo;

    public List<Programme> getAllProgrammes() {
        return programmeRepo.findAll();
    }

    public Page<Programme> findAllProgrammes(Pageable pageable) {
        return programmeRepo.findAll(pageable);
    }

    public Programme findProgrammeById(Long id) {
        Optional<Programme> programme = programmeRepo.findById(id);
        if(programme.isPresent()) {
            return programme.get();
        }
        else{
            throw new ResourceNotFoundException("Programme not found");
        }
    }

    public Programme saveProgramme(Programme programme) {
        return programmeRepo.save(programme);
    }

    public Programme updateProgramme(Long id,Programme programme) {
        Programme updatedProgramme = findProgrammeById(id);
        updatedProgramme.setOption(programme.getOption());
        return programmeRepo.save(updatedProgramme);
    }

    public void deleteProgramme(Long id) {
        Programme programme = findProgrammeById(id);
        programmeRepo.delete(programme);
    }
}
