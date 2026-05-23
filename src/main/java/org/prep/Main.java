package org.prep;

import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Total de precipitação para cada mês do ano:");
        Map<Integer, Float> map = new LinkedHashMap<>(Calculo.precipitacaoPorMes(0, 2025));
        System.out.println(map);

        System.out.println("Dia de maior precipitação no ano:");
        Map<Data, Float> map2a = new HashMap<>(Calculo.diaMaiorPrecipitacao(0, 2025, true));
        System.out.println(map2a);
        System.out.println("Dia de menor precipitação no ano:");
        Map<Data, Float> map2b = new HashMap<>(Calculo.diaMaiorPrecipitacao(0, 2025, false));
        System.out.println(map2b);

        System.out.println("Mês de maior e menor precipitação no ano:");
        Map<Integer, Float> map3 = new HashMap<>(Calculo.mesMaiorPrecipitacao(0, 2025));
        System.out.println(map3);

        System.out.println("Média de precipitação do ano:");
        float flt = Calculo.mediaPrecipAnual(2025);
        System.out.println(flt);

        System.out.println("Média da precipitação de cada mês do ano:");
        Map<Integer, Float> map4 = new HashMap<>(Calculo.mediaPrecipMensal(0, 2025));
        System.out.println(map4);

        System.out.println("Os 10 Dias de maior precipitação no ano:");
        Map<Integer, Precipitacao> map5 = new HashMap<>(Calculo.maioresPrecipNoAno(2025));
        System.out.println(map5);
    }
}
