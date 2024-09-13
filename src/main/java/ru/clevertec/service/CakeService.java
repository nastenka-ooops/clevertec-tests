package ru.clevertec.service;

import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import ru.clevertec.common.CakeType;
import ru.clevertec.domain.Cake;
import ru.clevertec.entity.CakeEntity;
import ru.clevertec.mapper.CakeMapper;
import ru.clevertec.repository.CakeRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CakeService {
    private final CakeRepository cakeRepository;
    private final CakeMapper cakeMapper;

    public Cake getCakeById(UUID id) {
        Optional<CakeEntity> cakeEntity = cakeRepository.getCakeById(id);
        return cakeEntity.map(cakeMapper::toDomain).orElse(null);
    }

    public List<Cake> getCakesByType(CakeType type) {
        List<CakeEntity> cakeEntities = cakeRepository.getCakeByType(type);
        return cakeMapper.toDomains(cakeEntities);
    }

    public List<Cake> getAllCakes() {
        List<CakeEntity> cakeEntities = cakeRepository.getAllCakes();
        return cakeMapper.toDomains(cakeEntities);
    }

    public Cake createCake(Cake cake) {
        CakeEntity cakeEntity = cakeRepository.createCake(cakeMapper.toEntity(cake));
        return cakeMapper.toDomain(cakeEntity);
    }

    public Cake updateCake(UUID id, Cake cake) {
        CakeEntity cakeEntity = cakeRepository.updateCake(id, cakeMapper.toEntity(cake));
        return cakeMapper.toDomain(cakeEntity);
    }

    public void deleteCake(UUID id) {
        cakeRepository.deleteCake(id);
    }

}
