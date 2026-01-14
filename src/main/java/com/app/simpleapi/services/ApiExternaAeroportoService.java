package com.app.simpleapi.services;

import com.app.simpleapi.domain.Aeroporto;
import com.app.simpleapi.providers.IAirportProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApiExternaAeroportoService {

   private final IAirportProvider airportProvider;

    public List<Aeroporto> processarGetAeroportos(String codigoAeroporto){
        System.out.println("Buscando aeroporto");
        return airportProvider.getAirport(codigoAeroporto);
    }
}
