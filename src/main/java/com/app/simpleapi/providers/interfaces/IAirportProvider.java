package com.app.simpleapi.providers.interfaces;

import com.app.simpleapi.domain.aeroporto.Aeroporto;

import java.util.List;

public interface IAirportProvider {
    List<Aeroporto> getAirport(String codigoAeroporto);
}
