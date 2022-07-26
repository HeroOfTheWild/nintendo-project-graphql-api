package com.kjam.graphQL.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Table("NINTENDO.FRANCHISE")
public class Franchise {

    @Id
    @Column("FRANCHISE_ID")
    String franchiseId;

    @Column("TITLE")
    String title;

    @Column("CREATOR_NM")
    String creator;
}
