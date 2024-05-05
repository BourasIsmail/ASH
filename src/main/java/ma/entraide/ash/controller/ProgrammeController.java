package ma.entraide.ash.controller;

import ma.entraide.ash.entity.Programme;
import ma.entraide.ash.service.ProgrammeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/programme")
public class ProgrammeController {
    @Autowired
    private ProgrammeService programmeService;

    @GetMapping("/all")
    public ResponseEntity<List<Programme>> getAllProgrammes() {
        List<Programme> programmes = programmeService.getAllProgrammes();
        return ResponseEntity.ok(programmes);
    }

    @GetMapping
    public ResponseEntity<Page<Programme>> getPrestations(@RequestParam(defaultValue = "0") Integer page,
                                                           @RequestParam(defaultValue = "10") Integer size,
                                                           @RequestParam(defaultValue = "nomProgramme") String sortField,
                                                           @RequestParam(defaultValue = "asc") String sortDirection) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sortField));
        Page<Programme> pages = programmeService.findAllProgrammes(pageable);
        return ResponseEntity.ok(pages);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Programme> getProgrammeById(@PathVariable Long id) {
        try {
            Programme programme = programmeService.findProgrammeById(id);
            return ResponseEntity.ok(programme);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Programme> addProgramme(@RequestBody Programme programme) {
        try {
            Programme newProgramme = programmeService.saveProgramme(programme);
            return ResponseEntity.ok(newProgramme);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Programme> updateProgramme(@PathVariable Long id, @RequestBody Programme programme) {
        try {
            Programme updatedProgramme = programmeService.updateProgramme(id, programme);
            return ResponseEntity.ok(updatedProgramme);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProgramme(@PathVariable Long id) {
        try {
            programmeService.deleteProgramme(id);
            return ResponseEntity.ok("Programme deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
