package com.app.simpleapi.domain.aviao;

import com.app.simpleapi.domain.aeroporto.Aeroporto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Setter
@Getter
public class Aviao {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String codigo;

    @Column(nullable = false)
    private String modelo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aeroporto_id", nullable = false)
    @JsonBackReference
    private Aeroporto aeroporto;
}
