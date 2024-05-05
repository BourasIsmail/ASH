package ma.entraide.ash.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/etablissement")
public class EtablissementController {
    @Autowired
    private EtablissementService etablissementService;

    @GetMapping("/all")
    public ResponseEntity<List<Etablissement>> getAllEtablissements() {
        List<Etablissement> etablissements = etablissementService.getAllEtablissements();
        return ResponseEntity.ok(etablissements);
    }

    @GetMapping
    public Page<Etablissement> getAllEtablissements(@RequestParam(defaultValue = "0") Integer page,
                                                    @RequestParam(defaultValue = "10") Integer size,
                                                    @RequestParam(defaultValue = "nomEtablissement") String sortField,
                                                    @RequestParam(defaultValue = "asc") Sort.Direction sortDirection) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sortField));

        return etablissementService.findAllEtablissements(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Etablissement> getEtablissementById(@PathVariable Long id) {
        try {
            Etablissement etablissement = etablissementService.findEtablissementById(id);
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

}
