package com.app.simpleapi.services;

import com.app.simpleapi.domain.aeroporto.Aeroporto;
import com.app.simpleapi.domain.aeroporto.AeroportoRepository;
import com.app.simpleapi.domain.aviao.Aviao;
import com.app.simpleapi.providers.interfaces.IAirportProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    private void SalvarAeroporto(List<Aeroporto> aeroportos){
        aeroportos.forEach(aeroporto -> {
            List<Aviao> avioes = aviaoFactory.criarAvioes(aeroporto);
            aeroporto.setAvioes(avioes);
            aeroportoRepository.save(aeroporto);
        });
    }
}
