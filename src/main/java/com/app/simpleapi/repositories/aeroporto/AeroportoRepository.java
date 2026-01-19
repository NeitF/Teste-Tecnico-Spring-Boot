package com.app.simpleapi.repositories.aeroporto;


import com.app.simpleapi.domain.Aeroporto;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AeroportoRepository extends Repository<Aeroporto, String>, AeroportoCustomRepository {

    Aeroporto save(Aeroporto aeroporto);

    void deleteByCodigoIcao(String codigoIcao);

    @EntityGraph(attributePaths = {"avioes"})
    Optional<Aeroporto> findByCodigoIcao(String codigoIcao);

    @Query("SELECT a FROM Aeroporto a JOIN FETCH a.avioes WHERE a.codigoIcao = :codigoIcao")
    Optional<Aeroporto> findByCodigoIcaoUsingHql(@Param("codigoIcao") String codigoIcao);

    @Query(nativeQuery = true, value = "SELECT a.* FROM aeroporto a INNER JOIN aviao av on a.codigo_icao = av.aeroporto_id where a.codigo_icao = :codigoIcao")
    Optional<Aeroporto> findByCodigoIcaoUsingNativeQuery(@Param("codigoIcao") String codigoIcao);
}
