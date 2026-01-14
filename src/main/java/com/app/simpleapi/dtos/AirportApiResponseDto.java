package com.app.simpleapi.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AirportApiResponseDto {

    @JsonProperty("facility_name")
    private String facilityName;

    @JsonProperty("faa_ident")
    private String faaIdent;

    @JsonProperty("icao_ident")
    private String icaoIdent;

    @JsonProperty("state_full")
    private String stateFull;

    @JsonProperty("county")
    private String county;

    @JsonProperty("city")
    private String city;

}