package io.fafee.serverless.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FooRepository extends JpaRepository<FooEntity, Long> {

    @Query("SELECT foo FROM FooEntity foo JOIN FETCH foo.bars")
    Optional<FooEntity> loadById(Long id);
}
