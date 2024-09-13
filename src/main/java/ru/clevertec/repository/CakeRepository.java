package ru.clevertec.repository;


import org.springframework.stereotype.Repository;
import ru.clevertec.common.CakeType;
import ru.clevertec.entity.CakeEntity;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class CakeRepository {
     List<CakeEntity> cakeEntities = List.of(
            new CakeEntity(UUID.randomUUID(), "Chocolate Delight", CakeType.CHOCOLATE, OffsetDateTime.now().plusDays(3)),
            new CakeEntity(UUID.randomUUID(), "Vanilla Dream", CakeType.VANILLA, OffsetDateTime.now().plusDays(5)),
            new CakeEntity(UUID.randomUUID(), "Strawberry Bliss", CakeType.STRAWBERRY, OffsetDateTime.now().plusDays(2))
    );

    public List<CakeEntity> getAllCakes() {
        return cakeEntities;
    }

    public Optional<CakeEntity> getCakeById(UUID id) {
        for (CakeEntity cake : cakeEntities) {
            if (cake.getId().equals(id)) {
                return Optional.of(cake);
            }
        }
        return Optional.empty();
    }

    public List<CakeEntity> getCakeByType(CakeType type) {
        List<CakeEntity> foundCakes = new ArrayList<>();
        for (CakeEntity cake : cakeEntities) {
            if (cake.getCakeType() == type) {
                foundCakes.add(cake);
            }
        }
        return foundCakes;
    }

    public CakeEntity createCake(CakeEntity cakeEntity) {
        cakeEntities.add(cakeEntity);
        return cakeEntity;
    }

    public CakeEntity updateCake(UUID id, CakeEntity updatedCakeEntity) {
        for (int i = 0; i < cakeEntities.size(); i++) {
            if (cakeEntities.get(i).getId().equals(id)) {
                cakeEntities.set(i, updatedCakeEntity);
                return updatedCakeEntity;
            }
        }
        return null;
    }

    public void deleteCake(UUID id) {
        cakeEntities.removeIf(cake -> cake.getId().equals(id));
    }
}
