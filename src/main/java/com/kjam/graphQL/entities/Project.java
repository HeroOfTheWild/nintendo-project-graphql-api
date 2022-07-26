package com.kjam.graphQL.entities;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("NINTENDO.PROJECT")
public class Project {

    @Id
    @Column("PROJECT_ID")
    private String projectId;

    @Column("TEAM_ID")
    private String teamId;

    @Column("FRANCHISE_ID")
    private String franchiseId;

    @Column("PROJECT_NM")
    private String projectName;

    @Column("PROJECT_STATUS")
    private ProjectStatus status;
    
    @Column("MODIFIED") 
    private Timestamp modified;

    @Transient
    private LocalDate startDate;

    @Transient
    private LocalDate endDate;

    public ZonedDateTime getLastModified() {
        return Optional.ofNullable(this.modified)
            .map(t -> ZonedDateTime.ofInstant(t.toInstant(), ZoneId.systemDefault()))
            .orElse(null);
    }
}
