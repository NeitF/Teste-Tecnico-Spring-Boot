package com.app.simpleapi.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Aeroporto {
    private String codigoIcao;
    private String nome;
    private String cidade;
    private String estado;
}
