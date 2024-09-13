package ru.clevertec.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.clevertec.domain.Cake;
import ru.clevertec.entity.CakeEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-13T16:54:35+0300",
    comments = "version: 1.6.0, compiler: javac, environment: Java 21.0.3 (Eclipse Adoptium)"
)
@Component
public class CakeMapperImpl implements CakeMapper {

    @Override
    public Cake toDomain(CakeEntity cakeEntity) {
        if ( cakeEntity == null ) {
            return null;
        }

        Cake.CakeBuilder cake = Cake.builder();

        cake.id( cakeEntity.getId() );
        cake.title( cakeEntity.getTitle() );
        cake.cakeType( cakeEntity.getCakeType() );
        cake.expiredPeriod( cakeEntity.getExpiredPeriod() );

        return cake.build();
    }

    @Override
    public List<Cake> toDomains(List<CakeEntity> cakeEntities) {
        if ( cakeEntities == null ) {
            return null;
        }

        List<Cake> list = new ArrayList<Cake>( cakeEntities.size() );
        for ( CakeEntity cakeEntity : cakeEntities ) {
            list.add( toDomain( cakeEntity ) );
        }

        return list;
    }

    @Override
    public CakeEntity toEntity(Cake cake) {
        if ( cake == null ) {
            return null;
        }

        CakeEntity cakeEntity = new CakeEntity();

        cakeEntity.setId( cake.getId() );
        cakeEntity.setTitle( cake.getTitle() );
        cakeEntity.setCakeType( cake.getCakeType() );
        cakeEntity.setExpiredPeriod( cake.getExpiredPeriod() );

        return cakeEntity;
    }
}
