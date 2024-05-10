package ma.entraide.ash.controller;

import ma.entraide.ash.entity.Prestation;
import ma.entraide.ash.service.PrestationService;
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
@RequestMapping("/prestation")
public class PrestationController {
    @Autowired
    private PrestationService prestationService;

    @GetMapping("/all")
    public ResponseEntity<List<Prestation>> getAllPrestations() {
        List<Prestation> prestations = prestationService.getAllPrestations();
        return ResponseEntity.ok(prestations);
    }

    @GetMapping
    public ResponseEntity<Page<Prestation>> getPrestations(@RequestParam(defaultValue = "0") Integer page,
                                                           @RequestParam(defaultValue = "10") Integer size,
                                                           @RequestParam(defaultValue = "nomPrestation") String sortField,
                                                           @RequestParam(defaultValue = "asc") String sortDirection) {
        Sort.Direction direction = Sort.Direction.valueOf(sortDirection.toUpperCase()); // Ensure uppercase for case-insensitive matching
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortField));
        Page<Prestation> pages = prestationService.findAllPrestations(pageable);
        return ResponseEntity.ok(pages);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prestation> getPrestationById(@PathVariable Long id) {
        try {
            Prestation prestation = prestationService.findPrestationById(id);
            return ResponseEntity.ok(prestation);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Prestation> addPrestation(@RequestBody Prestation prestation) {
        try {
            Prestation newPrestation = prestationService.savePrestation(prestation);
            return ResponseEntity.ok(newPrestation);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Prestation> updatePrestation(@PathVariable Long id, @RequestBody Prestation prestation) {
        try {
            Prestation updatedPrestation = prestationService.updatePrestation(id, prestation);
            return ResponseEntity.ok(updatedPrestation);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePrestation(@PathVariable Long id) {
        try {
            prestationService.deletePrestation(id);
            return ResponseEntity.ok("Prestation deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
