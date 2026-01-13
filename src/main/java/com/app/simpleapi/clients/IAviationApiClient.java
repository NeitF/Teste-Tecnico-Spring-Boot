package com.app.simpleapi.clients;

import com.app.simpleapi.dtos.AirportResponseDto;

import java.util.List;
import java.util.Map;

public interface IAviationApiClient {
    Map<String, List<AirportResponseDto>> getAirport(String codigoAeroporto);
}
