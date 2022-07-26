package com.kjam.graphQL.entities.records;

import java.util.List;

public record ProjectConnection(PageInfo pageInfo, List<ProjectConnectionEdge> edges) {}
