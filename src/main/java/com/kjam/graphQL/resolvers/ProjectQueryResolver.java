package com.kjam.graphQL.resolvers;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.kjam.graphQL.entities.Project;
import com.kjam.graphQL.entities.ProjectStatus;
import com.kjam.graphQL.entities.records.ProjectConnection;
import com.kjam.graphQL.entities.records.ProjectConnectionEdge;
import com.kjam.graphQL.entities.records.PageInfo;
import com.kjam.graphQL.repositories.ProjectRepository;

import graphql.kickstart.tools.GraphQLQueryResolver;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class ProjectQueryResolver implements GraphQLQueryResolver {

    private final ProjectRepository repository;

    @Async("ResolverThreadPool")
    public CompletableFuture<Project> project(String projectId) {
        return repository.findById(projectId).toFuture();
    }

    @Async("ResolverThreadPool")
    public CompletableFuture<ProjectConnection> projects(int page, int rows) {
        return repository.findAllBy(PageRequest.of(page, rows))
                            .collectList()
                            .zipWith(repository.count())
                            .map(t -> connection(edges(t.getT1()), page, rows, t.getT2()))
                            .toFuture();
    }

    @Async("ResolverThreadPool")
    public CompletableFuture<List<Project>> projectsByCriteria(String teamId, String franchiseId, ProjectStatus status) {
        if(allBlank(teamId, franchiseId) && Objects.isNull(status)) return null;
        var projectExample = Example.of(Project.builder().teamId(teamId).franchiseId(franchiseId).status(status).build());
        return repository.findAll(projectExample).collectList().toFuture();
    }

    protected List<ProjectConnectionEdge> edges(List<Project> projects) {
        return projects.stream().map(p -> new ProjectConnectionEdge(p.getProjectId(), p)).collect(Collectors.toList());
    }

    protected ProjectConnection connection(List<ProjectConnectionEdge> edges, int page, int rows, Long totalCount) {
        var pageInfo = new PageInfo(rows <= edges.size(), page > 0, page, totalCount.longValue());
        return new ProjectConnection(pageInfo, edges);
    }

    private boolean allBlank(String... values) {
        return Arrays.stream(values).allMatch(this::isBlank);
    }

    private boolean isBlank(String value) {
        return Objects.isNull(value) || value.isBlank();
    }

}
