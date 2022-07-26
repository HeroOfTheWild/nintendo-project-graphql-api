package com.kjam.graphQL.entities;

import lombok.Getter;

@Getter
public enum ProjectStatus {
    NEW, READY, INPROGRESS, BLOCKED, COMPLETED, TERMINATED, CANCELLED, RELEASED, REMASTER
}
