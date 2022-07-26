package com.kjam.graphQL.entities.records;

public record PageInfo(boolean hasNextPage, boolean hasPreviousPage, int page, long total) {}
