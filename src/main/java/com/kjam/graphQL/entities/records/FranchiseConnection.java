package com.kjam.graphQL.entities.records;

import java.util.List;

public record FranchiseConnection(PageInfo pageInfo, List<FranchiseConnectionEdge> edges) {}
