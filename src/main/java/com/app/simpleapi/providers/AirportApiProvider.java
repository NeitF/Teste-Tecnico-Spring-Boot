package com.app.simpleapi.providers;

import com.app.simpleapi.domain.Aeroporto;
import com.app.simpleapi.dtos.AirportApiResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class AirportApiProvider implements IAirportProvider {

    private final RestClient restClient;

    public AirportApiProvider(@Value("${api.aviationApi.url-base}") String baseUrl) {
        this.restClient = RestClient
                .builder()
                .baseUrl(baseUrl)
                .build();
    }

    @Override
    public List<Aeroporto> getAirport(String codigoAeroporto) {
        List<Aeroporto> aeroportos = new ArrayList<Aeroporto>();
        Map<String, List<AirportApiResponseDto>> response = restClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("airports")
                        .queryParam("apt", codigoAeroporto)
                        .build())
                .retrieve()
                .onStatus(status -> status.isError(), (req, resp) -> {
                    throw new RuntimeException();
                })
                .body(new ParameterizedTypeReference<Map<String, List<AirportApiResponseDto>>>() {});

        response.forEach((codigoIcao, values) ->{
            values.forEach(value -> {
                aeroportos.add(Aeroporto.builder()
                        .codigoIcao(codigoIcao)
                        .nome(value.getFacilityName())
                        .cidade(value.getCity())
                        .estado(value.getStateFull())
                        .build());
            });
        });

        return aeroportos;
    }

}
