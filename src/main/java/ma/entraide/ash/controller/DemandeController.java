package ma.entraide.ash.controller;

import ma.entraide.ash.entity.Demande;
import ma.entraide.ash.service.DemandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/demande")
public class DemandeController {
    @Autowired
    private DemandeService demandeService;

    @GetMapping
    public ResponseEntity<Page<Demande>> findAll(@RequestParam(defaultValue = "0") Integer page,
                                                 @RequestParam(defaultValue = "10") Integer size,
                                                 @RequestParam(defaultValue = "nomAssociation") String sortField,
                                                 @RequestParam(defaultValue = "asc") String sortDirection){
        Sort.Direction direction = Sort.Direction.valueOf(sortDirection.toUpperCase()); // Ensure uppercase for case-insensitive matching
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortField));
        Page<Demande> demandes = demandeService.getAllDemandes(pageable);
        return ResponseEntity.ok(demandes);
    }
}
