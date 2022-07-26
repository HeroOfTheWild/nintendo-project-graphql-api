package com.kjam.graphQL.entities.records;

import com.kjam.graphQL.entities.Project;

public record ProjectConnectionEdge(String cursor, Project node) {}
