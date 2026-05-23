package org.prep;

public class Data {
    private int ano;
    private int mes;
    private int dia;

    public Data(int ano, int mes, int dia) {
        if (mes < 1 || mes > 12) throw new IllegalArgumentException("Mês inválido: " + mes);
        if (dia < 1 || dia > 31) throw new IllegalArgumentException("Dia inválido: " + dia);
        this.ano = ano;
        this.mes = mes;
        this.dia = dia;
    }
    public Data() {
        this.ano = 0;
        this.mes = 0;
        this.dia = 0;
    }

    public int getAno() { return ano; }
    public int getMes() { return mes; }
    public int getDia() { return dia; }

    // AAAA-MM-DD
    public static Data parseData(String data) {
        String[] array = data.split("-");
        return new Data(
                Integer.parseInt(array[0]),
                Integer.parseInt(array[1]),
                Integer.parseInt(array[2])
        );
    }

    @Override
    public String toString() {
        return String.format("%04d-%02d-%02d", ano, mes, dia);
    }

    @Override
    public int hashCode() {
        return (((ano*12) + mes) * 31) + dia;
    }
}
