package ru.clevertec.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.clevertec.common.CakeType;
import ru.clevertec.domain.Cake;
import ru.clevertec.entity.CakeEntity;
import ru.clevertec.service.CakeService;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest
class CakeControllerTest {

    @MockBean
    CakeService cakeService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void shouldGetAllCakes() throws Exception {
        //given
        UUID cakeId = UUID.randomUUID();

        Cake cake = new Cake(cakeId, "cake", CakeType.CHOCOLATE, OffsetDateTime.now());
        List<Cake> cakes = List.of(cake);
        when(cakeService.getAllCakes()).thenReturn(cakes);
        //when then
        mockMvc.perform(get("/api/v1/cakes")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(cakes.size()))
                .andExpect(jsonPath("$[0].id").value(cakeId.toString()));
    }

    @Test
    void shouldGetCakeById() throws Exception {
        //given
        UUID cakeId = UUID.randomUUID();

        Cake cake = new Cake(cakeId, "cake", CakeType.CHOCOLATE, OffsetDateTime.now());

        when(cakeService.getCakeById(cakeId)).thenReturn(cake);
        //when then
        mockMvc.perform(get("/api/v1/cakes/{cakeId}", cakeId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(cakeId.toString()))
                .andExpect(jsonPath("$.title").value("cake"));

    }

    @Test
    void shouldCreateCake() throws Exception {
        //given
        UUID cakeId = UUID.randomUUID();

        Cake cake = new Cake(cakeId, "cake", CakeType.CHOCOLATE, OffsetDateTime.now());

        when(cakeService.createCake(any(Cake.class))).thenReturn(cake);
        //when then
        mockMvc.perform(post("/api/v1/cakes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cake)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(cakeId.toString()))
                .andExpect(jsonPath("$.title").value("cake"));
    }

    @Test
    void shouldUpdateCake() throws Exception {
        //given
        UUID cakeId = UUID.randomUUID();

        Cake cake = new Cake(cakeId, "cake", CakeType.CHOCOLATE, OffsetDateTime.now());

        when(cakeService.updateCake(any(UUID.class), any(Cake.class))).thenReturn(cake);
        //when then
        mockMvc.perform(put("/api/v1/cakes/{cakeId}", cakeId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cake)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(cakeId.toString()))
                .andExpect(jsonPath("$.title").value("cake"));
    }

    @Test
    void deleteCake() throws Exception {
        //given
        UUID cakeId = UUID.randomUUID();
        //when then
        mockMvc.perform(delete("/api/v1/cakes/{cakeId}", cakeId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}