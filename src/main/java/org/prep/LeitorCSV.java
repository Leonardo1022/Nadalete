package org.prep;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class LeitorCSV {
    private static final String caminhoArquivo = "src/main/resources/Pluviometria2025.csv";

    protected static List<Precipitacao> CSVParaList() {
        List<Precipitacao> lista = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            boolean primeiraLinha = true;

            while ((linha = br.readLine()) != null) {
                if (primeiraLinha) { // pula o cabeçalho
                    primeiraLinha = false;
                    continue;
                }

                String[] campos = linha.split(";");
                int id  = Integer.parseInt(campos[0].trim());
                float valor    = Float.parseFloat(campos[1].trim());
                Data data = Data.parseData(campos[2].trim());
                int posto = Integer.parseInt(campos[3].trim());

                lista.add(new Precipitacao(id, valor, data, posto));
            }
        } catch(IOException e) {
            System.out.println(e);
        }
        return lista;
    }

    protected static Map<Integer, Precipitacao> CSVParaMap() throws IOException {
        Map<Integer, Precipitacao> map = new TreeMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            boolean primeiraLinha = true;

            while ((linha = br.readLine()) != null) {
                if (primeiraLinha) { // pula o cabeçalho
                    primeiraLinha = false;
                    continue;
                }

                String[] campos = linha.split(";");
                int id  = Integer.parseInt(campos[0].trim());
                float valor    = Float.parseFloat(campos[1].trim());
                Data data = Data.parseData(campos[2].trim());
                int posto = Integer.parseInt(campos[3].trim());

                map.put(data.hashCode(), new Precipitacao(id, valor, posto));
            }
        }
        return map;
    }

    public static void main(String[] args) {
    }
}