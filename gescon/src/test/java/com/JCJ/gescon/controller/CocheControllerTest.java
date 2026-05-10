package com.JCJ.gescon.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.JCJ.gescon.Model.Coche;
import com.JCJ.gescon.Service.CocheService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CocheController.class)
class CocheControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CocheService cocheService;

    @Test
    void shouldCreateCar() throws Exception {
        Coche coche = Coche.builder()
                .identificador(20)
                .marca("Ford")
                .modelo("Focus")
                .cilindrada(1600)
                .build();

        when(cocheService.crearNuevoCoche(any(Coche.class))).thenReturn(coche);

        mockMvc.perform(post("/coches")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(coche)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.identificador").value(20))
                .andExpect(jsonPath("$.marca").value("Ford"))
                .andExpect(jsonPath("$.modelo").value("Focus"));
    }

    @Test
    void shouldRejectInvalidCilindrada() throws Exception {
        Coche coche = Coche.builder()
                .identificador(21)
                .marca("Ford")
                .modelo("Fiesta")
                .cilindrada(0)
                .build();

        mockMvc.perform(post("/coches")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(coche)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnCarsByMarca() throws Exception {
        when(cocheService.consultarCochesPorMarca("Seat")).thenReturn(List.of(
                new Coche(2, "Seat", "Leon", 1600),
                new Coche(5, "Seat", "Ibiza", 1400)
        ));

        mockMvc.perform(get("/coches/marca/Seat"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].marca").value("Seat"))
                .andExpect(jsonPath("$[1].modelo").value("Ibiza"));
    }

    @Test
    void shouldReturnEmptyListWhenMarcaDoesNotExist() throws Exception {
        when(cocheService.consultarCochesPorMarca("Tesla")).thenReturn(List.of());

        mockMvc.perform(get("/coches/marca/Tesla"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));
    }
}
