package ru.clevertec.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.clevertec.domain.Cake;
import ru.clevertec.service.CakeService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/cakes")
@AllArgsConstructor
public class CakeController {
    private final CakeService cakeService;

    @GetMapping
    public ResponseEntity<List<Cake>> getAllCakes() {
        List<Cake> cakes = cakeService.getAllCakes();
        return ResponseEntity.ok(cakes);
    }

    @GetMapping("/{cakeId}")
    public ResponseEntity<Cake> getCakeById(@PathVariable(name = "cakeId") UUID cakeId) {
        Cake cake = cakeService.getCakeById(cakeId);
        if (cake != null) {
            return ResponseEntity.ok(cake);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Cake> createCake(@RequestBody Cake cake) {
        Cake createdCake = cakeService.createCake(cake);
        return new ResponseEntity<>(createdCake, HttpStatus.CREATED);
    }

    @PutMapping("/{cakeId}")
    public ResponseEntity<Cake> updateCake(@PathVariable(name = "cakeId") UUID cakeId, @RequestBody Cake cake) {
        Cake updatedCake = cakeService.updateCake(cakeId, cake);
        if (updatedCake != null) {
            return ResponseEntity.ok(updatedCake);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{cakeId}")
    public ResponseEntity<Void> deleteCake(@PathVariable(name = "cakeId") UUID cakeId) {
        cakeService.deleteCake(cakeId);
        return ResponseEntity.noContent().build();
    }
}
