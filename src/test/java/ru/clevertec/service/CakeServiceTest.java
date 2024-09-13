package ru.clevertec.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.clevertec.common.CakeType;
import ru.clevertec.domain.Cake;
import ru.clevertec.entity.CakeEntity;
import ru.clevertec.mapper.CakeMapper;
import ru.clevertec.repository.CakeRepository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CakeServiceTest {
    @Mock
    CakeRepository cakeRepository;

    @Mock
    CakeMapper cakeMapper;

    @InjectMocks
    CakeService cakeService;

    @Test
    void shouldGetCakeById() {
        //given
        UUID cakeId = UUID.randomUUID();

        CakeEntity cakeEntity = new CakeEntity(cakeId, "cake", CakeType.CHOCOLATE, OffsetDateTime.now());
        Cake cake = new Cake(cakeId, "cake", CakeType.CHOCOLATE, OffsetDateTime.now());

        when(cakeRepository.getCakeById(cakeId)).thenReturn(Optional.of(cakeEntity));
        when(cakeMapper.toDomain(cakeEntity)).thenReturn(cake);
        //when
        Cake result = cakeService.getCakeById(cakeId);
        //then
        assertEquals(cake, result);
    }

    @Test
    void shouldGetAllCakes() {
        //given
        UUID cakeId = UUID.randomUUID();

        CakeEntity cakeEntity = new CakeEntity(cakeId, "cake", CakeType.CHOCOLATE, OffsetDateTime.now());
        Cake cake = new Cake(cakeId, "cake", CakeType.CHOCOLATE, OffsetDateTime.now());

        List<CakeEntity> cakeEntities = List.of(cakeEntity);
        List<Cake> cakes = List.of(cake);

        when(cakeRepository.getAllCakes()).thenReturn(cakeEntities);
        when(cakeMapper.toDomains(cakeEntities)).thenReturn(cakes);

        //when
        List<Cake> result = cakeService.getAllCakes();
        //then
        assertEquals(cake, result.getFirst());
    }

    @ParameterizedTest
    @EnumSource(CakeType.class)
    void shouldGetCakesByType(CakeType cakeType){
        //given
        UUID cakeId = UUID.randomUUID();

        CakeEntity cakeEntity = new CakeEntity(cakeId, "cake", cakeType, OffsetDateTime.now());
        Cake cake = new Cake(cakeId, "cake", cakeType, OffsetDateTime.now());

        List<CakeEntity> cakeEntities = List.of(cakeEntity);
        List<Cake> cakes = List.of(cake);

        when(cakeRepository.getCakeByType(cakeType)).thenReturn(cakeEntities);
        when(cakeMapper.toDomains(cakeEntities)).thenReturn(cakes);

        //when
        List<Cake> result = cakeService.getCakesByType(cakeType);
        //then
        assertEquals(cake, result.getFirst());
    }

    @Test
    void shouldCreateCake() {
        //given
        UUID cakeId = UUID.randomUUID();

        CakeEntity cakeEntity = new CakeEntity(cakeId, "cake", CakeType.CHOCOLATE, OffsetDateTime.now());
        Cake cake = new Cake(cakeId, "cake", CakeType.CHOCOLATE, OffsetDateTime.now());

        when(cakeMapper.toEntity(cake)).thenReturn(cakeEntity);
        when(cakeRepository.createCake(cakeEntity)).thenReturn(cakeEntity);
        when(cakeMapper.toDomain(cakeEntity)).thenReturn(cake);
        //when
        Cake result = cakeService.createCake(cake);
        //then
        assertEquals(cake, result);
    }

    @Test
    void shouldUpdateCake() {
        //given
        UUID cakeId = UUID.randomUUID();

        CakeEntity cakeEntity = new CakeEntity(cakeId, "cake", CakeType.CHOCOLATE, OffsetDateTime.now());
        Cake cake = new Cake(cakeId, "cake", CakeType.CHOCOLATE, OffsetDateTime.now());

        when(cakeMapper.toEntity(cake)).thenReturn(cakeEntity);
        when(cakeRepository.updateCake(cakeId, cakeEntity)).thenReturn(cakeEntity);
        when(cakeMapper.toDomain(cakeEntity)).thenReturn(cake);
        //when
        Cake result = cakeService.updateCake(cakeId, cake);
        //then
        assertEquals(cake, result);
    }

    @Test
    void deleteCake() {
        //given
        UUID cakeId = UUID.randomUUID();
        //when
        cakeService.deleteCake(cakeId);
        //then
        verify(cakeRepository).deleteCake(cakeId);
    }
}