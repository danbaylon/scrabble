package com.palo.it.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "score_entry")
@Getter
@Setter
@NoArgsConstructor
public class Entry extends UpdatableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "word", unique = true)
    private String word;

    @Column(name = "score")
    private Integer score;

    @PrePersist
    public void onCreate() {
        this.setCreatedBy("system");
        this.setCreatedDate(Instant.now());
    }

    @PreUpdate
    public void onUpdate() {
        this.setUpdatedBy("system");
        this.setUpdatedDate(Instant.now());
    }
}
