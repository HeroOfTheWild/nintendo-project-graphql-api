package com.kjam.graphQL.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;

import com.kjam.graphQL.entities.Project;

import reactor.core.publisher.Flux;


@Repository
public interface ProjectRepository extends ReactiveSortingRepository<Project, String> , ReactiveQueryByExampleExecutor<Project> {

    Flux<Project> findAllBy(Pageable pageable);
}
