package com.app.simpleapi.providers;

import com.app.simpleapi.domain.Aeroporto;

import java.util.List;

public interface IAirportProvider {
    List<Aeroporto> getAirport(String codigoAeroporto);
}
