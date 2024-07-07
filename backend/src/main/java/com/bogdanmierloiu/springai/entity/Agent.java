package com.bogdanmierloiu.springai.entity;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "agents")
public class Agent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private UUID uuid;

    @Column(nullable = false, unique = true)
    @Schema(description = "The name of the agent", example = "Magic Agent")
    private String name;

    @Schema(description = "The language of the agent", example = "English")
    private String language;

    @Schema(description = "The expertise of the agent", example = "AI, Machine Learning, NLP")
    private String expertise;

    @Schema(description = "The tone of the agent", example = "Formal, Informal, Friendly")
    private String tone;

    private String usage;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @JoinColumn(name = "trait_id", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private Trait trait;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_agents",
            joinColumns = @JoinColumn(name = "agent_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> owners;

    @PrePersist
    public void prePersist() {
        this.uuid = UUID.randomUUID();
    }

    @Override
    public String toString() {
        return "Agent{" +
                "name='" + name + '\'' +
                ", language='" + language + '\'' +
                ", expertise='" + expertise + '\'' +
                ", tone='" + tone + '\'' +
                ", usage='" + usage + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Agent agent = (Agent) o;
        return Objects.equals(id, agent.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
