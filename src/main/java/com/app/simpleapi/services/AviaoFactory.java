package com.app.simpleapi.services;

import com.app.simpleapi.domain.Aeroporto;
import com.app.simpleapi.domain.Aviao;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class AviaoFactory {

    public List<Aviao> criarAvioes(Aeroporto aeroporto) {
        List<Aviao> avioes = new ArrayList<>();
        Random rnd = new Random();
        for (int i = 0; i < 5; i++) {
            Aviao aviao = new Aviao();
            aviao.setCodigo(gerarCodigo(rnd));
            aviao.setModelo(gerarModelo(rnd));
            aviao.setAeroporto(aeroporto);
            avioes.add(aviao);
        }
        return avioes;
    }

    private String gerarCodigo(Random rnd) {
        String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder(6);

        for (int i = 0; i < 3; i++)
            sb.append(letras.charAt(rnd.nextInt(letras.length())));

        for (int i = 0; i < 3; i++)
            sb.append(rnd.nextInt(10));

        return sb.toString();
    }

    private String gerarModelo(Random rnd) {
        String[] base = {"A320", "B737", "E195", "A350", "B787", "A220"};
        return base[rnd.nextInt(base.length)] + "-" + (100 + rnd.nextInt(900)); // Ex: A320-457
    }
}
