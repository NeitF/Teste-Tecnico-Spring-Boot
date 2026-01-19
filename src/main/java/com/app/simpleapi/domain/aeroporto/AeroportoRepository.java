package com.app.simpleapi.domain.aeroporto;


import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface AeroportoRepository extends Repository<Aeroporto, String> {

    Aeroporto save(Aeroporto aeroporto);
    Optional<Aeroporto> findById(String codigoIcao);

    @EntityGraph(attributePaths = {"avioes"})
    Optional<Aeroporto> findByCodigoIcao(String codigoIcao);
}
