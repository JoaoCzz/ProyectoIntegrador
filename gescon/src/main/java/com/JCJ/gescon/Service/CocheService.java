package com.JCJ.gescon.Service;

import com.JCJ.gescon.Model.Coche;
import com.JCJ.gescon.Repository.CocheRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CocheService {

    private final CocheRepository cocheRepository;

    public CocheService(CocheRepository cocheRepository) {
        this.cocheRepository = cocheRepository;
    }

    public Coche crearNuevoCoche(Coche coche) {
        return cocheRepository.save(coche);
    }

    public List<Coche> consultarCochesPorMarca(String marca) {
        return cocheRepository.findByMarca(marca);
    }
}
