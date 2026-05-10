package com.JCJ.gescon.Repository;

import com.JCJ.gescon.Model.Coche;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class CocheRepositoryTest {

    @Autowired
    private CocheRepository cocheRepository;

    @Test
    void shouldFindCarsByMarca() {
        List<Coche> coches = cocheRepository.findByMarca("Seat");

        assertThat(coches).hasSize(3);
        assertThat(coches)
                .extracting(Coche::getModelo)
                .containsExactlyInAnyOrder("Leon", "Clio", "Ibiza");
    }

    @Test
    void shouldReturnEmptyListWhenMarcaDoesNotExist() {
        List<Coche> coches = cocheRepository.findByMarca("Tesla");

        assertThat(coches).isEmpty();
    }
}
