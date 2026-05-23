package org.prep;

import java.util.*;

public class Calculo {
    private static Set<Precipitacao> ordenarPrecipData() {
        List<Precipitacao> precipitacoes = new ArrayList<>(LeitorCSV.CSVParaList());
        return new TreeSet<>(precipitacoes);
    }
    private static Set<Precipitacao> ordenarPrecipValor() {
        List<Precipitacao> precipitacoes = new ArrayList<>(LeitorCSV.CSVParaList());
        precipitacoes.sort(Comparator.comparingDouble(p -> p.getValor()));
        return new LinkedHashSet<>(precipitacoes);
    }

    private static Set<Precipitacao> periodizarPrecipitacoes(int mes, int ano) {
        return periodizarPrecipitacoes(mes, ano, false);
    }

    private static Set<Precipitacao> periodizarPrecipitacoes(int mes, int ano, boolean valor) {
        Set<Precipitacao> periodo = new LinkedHashSet<>();
        Set<Precipitacao> a;

        if(valor) {
            a = ordenarPrecipValor();
        }
        else {
            a = ordenarPrecipData();
        }

        for(Precipitacao p : a) {
            if(p.getData().getAno() == ano) {
                if(p.getData().getMes() == mes)
                    periodo.add(p);
                else if(mes == 0) {
                    periodo.add(p);
                }
            }
        }
        return periodo;
    }

    public static Map<Integer, Float> precipitacaoPorMes(int mes, int ano) {
        Set<Precipitacao> periodo = new LinkedHashSet<>(periodizarPrecipitacoes(mes, ano));
        Map<Integer, Float> precip_mensal = new LinkedHashMap<>();

        for(Precipitacao p : periodo) {
            int mes_atual = p.getData().getMes();
            float acumulado = precip_mensal.getOrDefault(mes_atual, 0f);
            precip_mensal.put(mes_atual, acumulado + p.getValor());
        }
        return precip_mensal;
    }

    public static Map<Data, Float> diaMaiorPrecipitacao(int mes, int ano, boolean maior) {
        Set<Precipitacao> periodo = periodizarPrecipitacoes(mes, ano, true);
        Precipitacao resultado_precip = new Precipitacao();
        if(maior) {
            resultado_precip = new LinkedHashSet<>(periodo).getFirst();
        }
        else {
            resultado_precip = new LinkedHashSet<>(periodo).getLast();
        }

        /*
        Set<Precipitacao> periodo = new LinkedHashSet<>(periodizarPrecipitacoes(mes, ano));
        Map<Data, Float> maior_prec = new LinkedHashMap<>();
        float maior_valor = 0;
        Data data_maior_prec = new Data();

        for(Precipitacao p : periodo) {
            float precip_atual = p.getValor();
            if(precip_atual > maior_valor) {
                maior_valor = precip_atual;
                data_maior_prec = p.getData();
            }
        }
        maior_prec.put(data_maior_prec, maior_valor);
        return maior_prec;
         */
        Map<Data, Float> resultado = new HashMap<Data, Float>();
        resultado.put(resultado_precip.getData(), resultado_precip.getValor());
        return resultado;
    }

    public static Map<Integer, Float> mesMaiorPrecipitacao(int mes, int ano) {
        Map<Integer, Float> precip_mensal = new TreeMap<>(precipitacaoPorMes(mes, ano));
        Integer maior_mes = 0;
        float maior_valor = 0;
        Map<Integer, Float> m = new HashMap<>();

        for(Map.Entry<Integer, Float> e : precip_mensal.entrySet()) {
            float valor_atual = e.getValue();
            if(valor_atual > maior_valor) {
                maior_mes = e.getKey();
                maior_valor = valor_atual;
            }
        }
        m.put(maior_mes, maior_valor);
        return m;
    }

    public static float mediaPrecipAnual(int ano) {
        Map<Integer, Float> precip_mensal = new TreeMap<>(precipitacaoPorMes(0, ano));
        float valor_total = 0;

        for(float v : precip_mensal.values()) {
            valor_total += v;
        }
        return valor_total / 365;
    }

    public static Map<Integer, Float> mediaPrecipMensal(int mes,int ano) {
        Set<Precipitacao> periodo = new LinkedHashSet<>(periodizarPrecipitacoes(mes, ano));
        Map<Integer, Float> med_precip_mensal = new LinkedHashMap<>();
        int i = 0;
        boolean anterior = true;
        int mes_anterior = 0;

        for(Precipitacao p : periodo) {
            int mes_atual = p.getData().getMes();
            if(anterior) {
                mes_anterior = mes_atual;
                anterior = false;
            }
            float acumulado = med_precip_mensal.getOrDefault(mes_atual, 0f);
            med_precip_mensal.put(mes_atual, acumulado + p.getValor());
            if(mes_atual != mes_anterior) {
                float total_mes_ant = med_precip_mensal.get(mes_anterior);
                med_precip_mensal.put(mes_anterior, total_mes_ant / i);
                mes_anterior = mes_atual;
                i = 0;
            }
            i++;
        }
        return med_precip_mensal;
    }

    public static Map<Integer, Precipitacao> maioresPrecipNoAno(int ano) {
        Set<Precipitacao> periodo = periodizarPrecipitacoes(0, ano, true);

        Map<Integer, Precipitacao> maiores_precip = new LinkedHashMap<>();
        int i = 1;

        for(Precipitacao p : periodo) {
            if(i >= 11) {
                return maiores_precip;
            }
            maiores_precip.put(i, p);
            i++;
        }
        return maiores_precip;
    }
}
