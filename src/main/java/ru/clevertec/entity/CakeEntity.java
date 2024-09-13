package ru.clevertec.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.clevertec.common.CakeType;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CakeEntity {
    private UUID id;
    private String title;
    private CakeType cakeType;
    private OffsetDateTime expiredPeriod;
}
