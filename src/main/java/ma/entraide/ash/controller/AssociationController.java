package ma.entraide.ash.controller;

import ma.entraide.ash.entity.Association;
import ma.entraide.ash.service.AssociationService;
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
@RequestMapping("/association")
public class AssociationController {

    @Autowired
    private AssociationService associationService;

    @GetMapping("/all")
    public ResponseEntity<List<Association>> getAllAssociations() {
        List<Association> associations = associationService.getAllAssociations();
        return ResponseEntity.ok(associations);
    }

    @GetMapping
    public ResponseEntity<Page<Association>> getAllAssociations(@RequestParam(defaultValue = "0") Integer page,
                                                @RequestParam(defaultValue = "10") Integer size,
                                                @RequestParam(defaultValue = "nomAssociation") String sortField,
                                                @RequestParam(defaultValue = "asc") String sortDirection) {
        Sort.Direction direction = Sort.Direction.valueOf(sortDirection.toUpperCase()); // Ensure uppercase for case-insensitive matching
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortField));
        Page<Association> pages= associationService.findAllAssociations(pageable);

        return ResponseEntity.ok(pages);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Association> getAssociationById(@PathVariable Long id) {
        try {
            Association association = associationService.findAssociationById(id);
            return ResponseEntity.ok(association);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/createAssociation")
    public ResponseEntity<Association> addAssociation(@RequestBody Association association) {
        try {
            Association newAssociation = associationService.saveAssociation(association);
            return new ResponseEntity<>(newAssociation, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Association> updateAssociation(@PathVariable Long id, @RequestBody Association association) {
        try {
            Association updatedAssociation = associationService.updateAssociation(id, association);
            return new ResponseEntity<>(updatedAssociation, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteAssociation(@PathVariable Long id) {
        try {
            associationService.deleteAssociation(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
