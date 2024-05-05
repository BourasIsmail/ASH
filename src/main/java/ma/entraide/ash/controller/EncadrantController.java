package ma.entraide.ash.controller;

import ma.entraide.ash.entity.Encadrant;
import ma.entraide.ash.service.EncadrantService;
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
@RequestMapping("/encadrant")
public class EncadrantController {
    @Autowired
    private EncadrantService encadrantService;

    @GetMapping("/all")
    public ResponseEntity<List<Encadrant>> getAllEncadrants() {
        List<Encadrant> encadrants = encadrantService.getAllEncadrants();
        return ResponseEntity.ok(encadrants);
    }

    @GetMapping
    public ResponseEntity<Page<Encadrant>> getEncadrants(@RequestParam(defaultValue = "0") Integer page,
                                                       @RequestParam(defaultValue = "10") Integer size,
                                                       @RequestParam(defaultValue = "nom") String sortField,
                                                       @RequestParam(defaultValue = "asc") Sort.Direction sortDirection) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sortField));
        Page<Encadrant> pages = encadrantService.findAllEncadrants(pageable);
        return ResponseEntity.ok(pages);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Encadrant> getEncadrantById(@PathVariable Long id) {
        try {
            Encadrant encadrant = encadrantService.findEncadrantById(id);
            return ResponseEntity.ok(encadrant);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Encadrant> addEncadrant(@RequestBody Encadrant encadrant) {
        try {
            Encadrant newEncadrant = encadrantService.saveEncadrant(encadrant);
            return ResponseEntity.ok(newEncadrant);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Encadrant> updateEncadrant(@PathVariable Long id, @RequestBody Encadrant encadrant) {
        try {
            Encadrant updatedEncadrant = encadrantService.updateEncadrant(id, encadrant);
            return ResponseEntity.ok(updatedEncadrant);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEncadrant(@PathVariable Long id) {
        try {
            encadrantService.deleteEncadrant(id);
            return ResponseEntity.ok("Encadrant deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
