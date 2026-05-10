package com.JCJ.gescon.Controller;

import com.JCJ.gescon.Model.Coche;
import com.JCJ.gescon.Service.CocheService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/coches")
public class CocheController {

    private final CocheService cocheService;

    public CocheController(CocheService cocheService) {
        this.cocheService = cocheService;
    }

    @PostMapping
    public ResponseEntity<Coche> addCoche(@Valid @RequestBody Coche coche) {
        return new ResponseEntity<>(cocheService.crearNuevoCoche(coche), HttpStatus.CREATED);
    }

    @GetMapping("/marca/{marca}")
    public ResponseEntity<List<Coche>> getCochesPorMarca(@PathVariable String marca) {
        return ResponseEntity.ok(cocheService.consultarCochesPorMarca(marca));
    }
}
