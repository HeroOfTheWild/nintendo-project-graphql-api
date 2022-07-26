package com.kjam.graphQL.resolvers;

import java.util.List;
import java.util.Objects;

import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.kjam.graphQL.entities.Franchise;
import com.kjam.graphQL.entities.records.FranchiseConnection;
import com.kjam.graphQL.entities.records.FranchiseConnectionEdge;
import com.kjam.graphQL.entities.records.PageInfo;
import com.kjam.graphQL.repositories.FranchiseRepository;

import graphql.kickstart.tools.GraphQLQueryResolver;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;

@AllArgsConstructor
@Component
public class FranchiseQueryResolver implements GraphQLQueryResolver {

    private final FranchiseRepository repository;

    @Async("ResolverThreadPool")
    public CompletableFuture<Franchise> franchise(String franchiseId) {
        return repository.findById(franchiseId).toFuture();
    }

    @Async("ResolverThreadPool")
    public CompletableFuture<FranchiseConnection> franchises(int page, int rows, String creatorName) {
        var pageRequest = PageRequest.of(page, rows);
        var isBlank = isBlank(creatorName);

        Flux<Franchise> fluxFranchise = isBlank
                                            ? repository.findAllBy(pageRequest) 
                                            : repository.findAllByCreator(creatorName, pageRequest);

        return fluxFranchise.collectList()
                            .zipWith(isBlank ? repository.count() : repository.countByCreator(creatorName))
                            .map(t -> connection(edges(t.getT1()), page, rows, t.getT2()))
                            .toFuture(); 
    }

    protected List<FranchiseConnectionEdge> edges(List<Franchise> franchises) {
        return franchises.stream().map(f -> new FranchiseConnectionEdge(f.getFranchiseId(), f)).collect(Collectors.toList());
    }

    protected FranchiseConnection connection(List<FranchiseConnectionEdge> edges, int page, int rows, Long totalCount) {
        var pageInfo = new PageInfo(rows <= edges.size(), page > 0, page, totalCount.longValue());
        return new FranchiseConnection(pageInfo, edges);
    }

    private boolean isBlank(String value) {
        return Objects.isNull(value) || value.isBlank();
    }
    
}
