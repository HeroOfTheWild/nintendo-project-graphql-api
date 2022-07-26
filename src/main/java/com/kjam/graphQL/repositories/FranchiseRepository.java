package com.kjam.graphQL.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;

import com.kjam.graphQL.entities.Franchise;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface FranchiseRepository extends ReactiveSortingRepository<Franchise, String> {

    Flux<Franchise> findAllBy(Pageable pageable);

    Flux<Franchise> findAllByCreator(String creator, Pageable pageable);

    @Query("SELECT COUNT(FRANCHISE_ID) FROM NINTENDO.FRANCHISE WHERE CREATOR_NM = $1")
    Mono<Long> countByCreator(String creator);
}
