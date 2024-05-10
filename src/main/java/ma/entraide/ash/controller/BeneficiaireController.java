package ma.entraide.ash.controller;

import ma.entraide.ash.entity.Beneficiaire;
import ma.entraide.ash.service.BeneficiaireService;
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
@RequestMapping("/beneficiaire")
public class BeneficiaireController {
    @Autowired
    private BeneficiaireService beneficiaireService;

    @GetMapping("/all")
    public ResponseEntity<List<Beneficiaire>> getAllBeneficiaires() {
        List<Beneficiaire> beneficiaires = beneficiaireService.getAllBeneficiaires();
        return ResponseEntity.ok(beneficiaires);
    }

    @GetMapping
    public ResponseEntity<Page<Beneficiaire>> getBeneficiaires(@RequestParam(defaultValue = "0") Integer page,
                                                               @RequestParam(defaultValue = "10") Integer size,
                                                               @RequestParam(defaultValue = "nom") String sortField,
                                                               @RequestParam(defaultValue = "asc") String sortDirection) {
        Sort.Direction direction = Sort.Direction.valueOf(sortDirection.toUpperCase()); // Ensure uppercase for case-insensitive matching
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortField));
        Page<Beneficiaire> pages = beneficiaireService.findAllBeneficiaires(pageable);
        return ResponseEntity.ok(pages);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Beneficiaire> getBeneficiaireById(@PathVariable Long id) {
        try {
            Beneficiaire beneficiaire = beneficiaireService.findBeneficiaireById(id);
            return ResponseEntity.ok(beneficiaire);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Beneficiaire> addBeneficiaire(@RequestBody Beneficiaire beneficiaire) {
        try {
            Beneficiaire newBeneficiaire = beneficiaireService.saveBeneficiaire(beneficiaire);
            return ResponseEntity.ok(newBeneficiaire);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Beneficiaire> updateBeneficiaire(@PathVariable Long id, @RequestBody Beneficiaire beneficiaire) {
        try {
            Beneficiaire updatedBeneficiaire = beneficiaireService.updateBeneficiaire(id, beneficiaire);
            return ResponseEntity.ok(updatedBeneficiaire);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBeneficiaire(@PathVariable Long id) {
        try {
            beneficiaireService.deleteBeneficiaire(id);
            return ResponseEntity.ok("Beneficiaire supprimé");
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
