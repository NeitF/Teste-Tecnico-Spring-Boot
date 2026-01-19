package com.app.simpleapi.repositories.aeroporto;

import com.app.simpleapi.domain.Aeroporto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.JoinType;
import java.util.Optional;

public class AeroportoCustomRepositoryImpl implements AeroportoCustomRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Aeroporto> findByCodigoIcaoUsingCriteria(String codigoIcao) {
        var cb = entityManager.getCriteriaBuilder();
        var cq = cb.createQuery(Aeroporto.class);
        var root = cq.from(Aeroporto.class);

        root.fetch("avioes", JoinType.INNER);

        cq.select(root)
            .where(cb.equal(root.get("codigoIcao"), codigoIcao));

        var result = entityManager
            .createQuery(cq)
            .getSingleResult();

        return Optional.ofNullable(result);
    }
}
