package com.app.simpleapi.services;

import com.app.simpleapi.clients.AviationApiClient;
import com.app.simpleapi.dtos.AirportResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ApiExternaAeroportoService {

   private final AviationApiClient aviationApiClient;

    public Map<String, List<AirportResponseDto>> processarGetAeroportos(String codigoAeroporto){
        System.out.println("Buscando aeroporto");
        return aviationApiClient.getAirport(codigoAeroporto);
    }
}
