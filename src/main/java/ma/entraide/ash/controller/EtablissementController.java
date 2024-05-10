package ma.entraide.ash.controller;

import ma.entraide.ash.entity.Etablissement;
import ma.entraide.ash.service.EtablissementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/etablissement")
public class EtablissementController {
    @Autowired
    private EtablissementService etablissementService;

    @GetMapping("/all")
    public ResponseEntity<List<Etablissement>> getAllEtablissements() {
        List<Etablissement> etablissements = etablissementService.getAllEtablissement();
        return ResponseEntity.ok(etablissements);
    }

    @GetMapping
    public ResponseEntity<Page<Etablissement>> getAllEtablissements(@RequestParam(defaultValue = "0") Integer page,
                                                    @RequestParam(defaultValue = "10") Integer size,
                                                    @RequestParam(defaultValue = "nomEtablissement") String sortField,
                                                    @RequestParam(defaultValue = "asc") String sortDirection) {
        Sort.Direction direction = Sort.Direction.valueOf(sortDirection.toUpperCase()); // Ensure uppercase for case-insensitive matching
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortField));

        Page<Etablissement> pages = etablissementService.findAllEtablissement(pageable);

        return ResponseEntity.ok(pages);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Etablissement> getEtablissementById(@PathVariable Long id) {
        try {
            Etablissement etablissement = etablissementService.getEtablissementById(id);
            return ResponseEntity.ok(etablissement);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Etablissement> addEtablissement(@RequestBody Etablissement etablissement) {
        try {
            Etablissement newEtablissement = etablissementService.saveEtablissement(etablissement);
            return new ResponseEntity<>(newEtablissement, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Etablissement> updateEtablissement(@PathVariable Long id, @RequestBody Etablissement etablissement) {
        try {
            Etablissement updatedEtablissement = etablissementService.updateEtablissement(id, etablissement);
            return ResponseEntity.ok(updatedEtablissement);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEtablissement(@PathVariable Long id) {
        try {
            etablissementService.deleteEtablissement(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{nomAssociation}")
    public ResponseEntity<?> getEtablissementByNomAssociation(@PathVariable String nomAssociation) {
        try {
            List<Etablissement> etablissements = etablissementService.getEtablissementByAssociation(nomAssociation);
            return ResponseEntity.ok(etablissements);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
