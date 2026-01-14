package com.app.simpleapi;

import com.app.simpleapi.services.ApiExternaAeroportoService;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SimpleApiApplicationTests {

    private static final WireMockServer mockedServer = new WireMockServer(8081);

    @Autowired
    private ApiExternaAeroportoService airportService;

    @BeforeAll
    static void startServer(){
        mockedServer.start();
        WireMock.configureFor("localhost", 8081);
    }

    @BeforeEach
    void resetWireMock() {
        mockedServer.resetAll();
    }

    @AfterAll
    static void stopServer(){
        mockedServer.stop();
    }

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry){
        registry.add("api.aviationApi.url-base", () -> "http://localhost:8081/");
    }

    @Test
    public void Deve_Retornar_Dados_Aeroporto() {
        // Assert
        var codIcao = "KMIA";
        var facilityName = "MIAMI INTL";
        var city = "MIAMI";
        var jsonRetorno = """
            {
              "%s": [
                {
                  "facility_name": "%s",
                  "city": "%s"
                }
              ]
            }
            """.formatted(codIcao, facilityName, city);

        stubFor(get(urlPathEqualTo("/airports"))
                .withQueryParam("apt", equalTo("KMIA"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(jsonRetorno)
                        .withStatus(200)));

        // Act
        var response = airportService.processarGetAeroportos("KMIA")
                .stream()
                .findFirst()
                .orElse(null);

        // Assert
        assert response != null;
        assertThat(response.getNome()).isEqualTo(facilityName);
        assertThat(response.getCidade()).isEqualTo(city);
        assertThat(response.getCodigoIcao()).isEqualTo(codIcao);
    }

}
