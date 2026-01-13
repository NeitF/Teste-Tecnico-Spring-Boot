package com.app.simpleapi.controllers;

import com.app.simpleapi.dtos.AirportResponseDto;
import com.app.simpleapi.services.ApiExternaAeroportoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CallApiExternaController {

    private final ApiExternaAeroportoService airportApiService;

    @GetMapping("/aeroportos")
    public ResponseEntity<Map<String, List<AirportResponseDto>>> ConsultarAeroporto(@RequestParam String codigo){
        var result = airportApiService.processarGetAeroportos(codigo);
        return ResponseEntity.ok(result);
    }

}
