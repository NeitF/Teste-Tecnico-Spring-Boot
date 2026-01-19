package com.app.simpleapi.repositories.aeroporto;

import com.app.simpleapi.domain.Aeroporto;

import java.util.Optional;

public interface AeroportoCustomRepository {
    Optional<Aeroporto> findByCodigoIcaoUsingCriteria(String codigoIcao);
}
