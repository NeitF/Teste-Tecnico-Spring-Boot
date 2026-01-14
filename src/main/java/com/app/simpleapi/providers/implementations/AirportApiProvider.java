package com.app.simpleapi.providers.implementations;

import com.app.simpleapi.domain.Aeroporto;
import com.app.simpleapi.dtos.AirportApiResponseDto;
import com.app.simpleapi.exceptions.UserFriendlyException;
import com.app.simpleapi.providers.interfaces.IAirportProvider;
import com.app.simpleapi.utils.AirportApiConsts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

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
        var aeroportos = new ArrayList<Aeroporto>();
        var response = CallApiGetAirport(codigoAeroporto);

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

    public Map<String, List<AirportApiResponseDto>> CallApiGetAirport(String codigoAeroporto) {
        int tentativasMaximas = 3;
        int tentativaAtual = 1;

        while (true) {
            try {
                return restClient
                        .get()
                        .uri(uriBuilder -> uriBuilder
                            .path("airports")
                            .queryParam("apt", codigoAeroporto)
                            .build())
                        .retrieve()
                        .body(new ParameterizedTypeReference<Map<String, List<AirportApiResponseDto>>>() {});

            } catch (HttpClientErrorException | HttpServerErrorException e) {
                if (tentativaAtual >= tentativasMaximas)
                    throw new UserFriendlyException(AirportApiConsts.ErrorMessageErroBuscaAeroporto);

                tentativaAtual++;
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException(ex);
                }

            } catch (Exception e) {
                throw new UserFriendlyException(AirportApiConsts.ErrorMessageErroBuscaAeroporto);
            }
        }
    }

}
