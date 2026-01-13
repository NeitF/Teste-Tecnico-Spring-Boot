package com.app.simpleapi.clients;

import com.app.simpleapi.dtos.AirportResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Map;

@Component
public class AviationApiClient implements IAviationApiClient{

    private final RestClient restClient;

    public AviationApiClient(@Value("${api.aviationApi.url-base}") String baseUrl) {
        this.restClient = RestClient
            .builder()
            .baseUrl(baseUrl)
            .build();
    }

    @Override
    public Map<String, List<AirportResponseDto>> getAirport(String codigoAeroporto) {
        var tentivasMaximas = 3;
        var tentativaAtual = 1;

        while(true){
            try{
                return restClient
                    .get()
                    .uri(uriBuilder -> uriBuilder
                            .path("airports")
                            .queryParam("apt", codigoAeroporto)
                            .build())
                    .retrieve()
                    .onStatus(status -> status.isError(), (req, resp) -> {
                        throw new RuntimeException();
                    })
                    .body(new ParameterizedTypeReference<Map<String, List<AirportResponseDto>>>() {});
            } catch (RuntimeException e) {
                if (tentativaAtual > tentivasMaximas)
                    throw new RuntimeException("Ocorreu um erro!");

                tentativaAtual++;
            }
        }
    }
}
