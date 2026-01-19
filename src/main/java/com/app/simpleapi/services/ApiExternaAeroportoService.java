package com.app.simpleapi.services;

import com.app.simpleapi.domain.Aeroporto;
import com.app.simpleapi.repositories.aeroporto.AeroportoRepository;
import com.app.simpleapi.domain.Aviao;
import com.app.simpleapi.providers.interfaces.IAirportProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ApiExternaAeroportoService {

   private final IAirportProvider airportProvider;
   private final AeroportoRepository aeroportoRepository;
   private final AviaoFactory aviaoFactory;

    public List<Aeroporto> ProcessarGetAeroportos(String codigoAeroporto){
        System.out.println("Buscando aeroporto");

        var aeroportos = airportProvider.getAirport(codigoAeroporto);

        SalvarAeroporto(aeroportos);

        return aeroportos;
    }

    public Aeroporto BuscarAeroportoMethodQuery(String codigoAeroporto){
        return aeroportoRepository
                .findByCodigoIcao(codigoAeroporto)
                .orElse(null);
    }

    public Aeroporto BuscarAeroportoHql(String codigoAeroporto){
        return aeroportoRepository
                .findByCodigoIcaoUsingHql(codigoAeroporto)
                .orElse(null);
    }

    public Aeroporto BuscarAeroportoNativeQuery(String codigoAeroporto){
        return aeroportoRepository
                .findByCodigoIcaoUsingNativeQuery(codigoAeroporto)
                .orElse(null);
    }

    public Aeroporto BuscarAeroportoCriteriaApi(String codigoAeroporto){
        return aeroportoRepository
                .findByCodigoIcaoUsingCriteria(codigoAeroporto)
                .orElse(null);
    }


    private void SalvarAeroporto(List<Aeroporto> aeroportos){
        aeroportos.forEach(aeroporto -> {
            List<Aviao> avioes = aviaoFactory.criarAvioes(aeroporto);
            aeroporto.setAvioes(avioes);
            aeroportoRepository.save(aeroporto);
        });
    }
}
