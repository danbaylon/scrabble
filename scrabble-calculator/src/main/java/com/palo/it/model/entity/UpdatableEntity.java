package com.palo.it.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class UpdatableEntity extends BaseAuditingEntity {

    @LastModifiedBy
    @Column(name = "updated_by")
    @JsonIgnore
    protected String updatedBy = "system"; //default as system

    @LastModifiedDate
    @Column(name = "updated_date")
    @JsonIgnore
    protected Instant updatedDate = Instant.now();
}
