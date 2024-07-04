package com.bogdanmierloiu.SpringAI.VectorAPI.entity;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "ai_agents")
public class Agent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @Schema(description = "The name of the agent", example = "Magic Agent")
    private String name;

    @Schema(description = "The personality of the agent", example = "Friendly, Helpful, Funny")
    private String personality;

    @Schema(description = "The language of the agent", example = "English")
    private String language;

    @Schema(description = "The expertise of the agent", example = "AI, Machine Learning, NLP")
    private String expertise;

    @Schema(description = "The tone of the agent", example = "Formal, Informal, Friendly")
    private String tone;

    private String format;

    private String usage;

    //    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "owner_id", nullable = false)
//    private Object owner;


    @Override
    public String toString() {
        return "Agent{" +
                "name='" + name + '\'' +
                ", personality='" + personality + '\'' +
                ", language='" + language + '\'' +
                ", expertise='" + expertise + '\'' +
                ", tone='" + tone + '\'' +
                ", format='" + format + '\'' +
                ", ussage='" + usage + '\'' +
                '}';
    }
}
