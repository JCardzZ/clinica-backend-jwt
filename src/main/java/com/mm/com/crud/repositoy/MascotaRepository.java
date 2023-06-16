package com.mm.com.crud.repositoy;

import com.mm.com.crud.entity.MascotaEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MascotaRepository extends MongoRepository<MascotaEntity, Integer> {
    boolean existsByDui(String dui);
    Optional<MascotaEntity> findByDui(String dui);
}
