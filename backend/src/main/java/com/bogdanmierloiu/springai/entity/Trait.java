package com.bogdanmierloiu.springai.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "traits")
public class Trait {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "empathy", length = 128)
    private String empathy;

    @Column(name = "reliability", length = 128)
    private String reliability;

    @Column(name = "confidence", length = 128)
    private String confidence;

    @Column(name = "attention_to_detail", length = 128)
    private String attentionToDetail;

    @Column(name = "adaptability", length = 128)
    private String adaptability;

    @Column(name = "patience", length = 128)
    private String patience;

    @Column(name = "communication", length = 128)
    private String communication;

    @Column(name = "innovation", length = 128)
    private String innovation;

    @Column(name = "resilience", length = 128)
    private String resilience;

    @Column(name = "collaboration", length = 128)
    private String collaboration;

    @Override
    public String toString() {
        return "Traits:" +
                collaboration + ", " +
                resilience + ", " +
                innovation + ", " +
                communication + ", " +
                patience + ", " +
                adaptability + ", " +
                attentionToDetail + ", " +
                confidence + ", " +
                reliability + ", " +
                empathy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trait trait = (Trait) o;
        return Objects.equals(id, trait.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
