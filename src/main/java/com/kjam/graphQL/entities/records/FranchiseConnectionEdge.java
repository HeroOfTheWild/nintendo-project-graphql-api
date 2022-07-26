package com.kjam.graphQL.entities.records;

import com.kjam.graphQL.entities.Franchise;

public record FranchiseConnectionEdge(String cursor, Franchise node) {}
