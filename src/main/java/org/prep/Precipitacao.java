package org.prep;

public class Precipitacao implements Comparable<Precipitacao> {
    private int id;
    private float valor;
    private Data data;
    private int posto;

    public Precipitacao(int id, float valor, Data data, int posto) {
        this.id = id;
        this.valor = valor;
        this.data = data;
        this.posto = posto;
    }

    public Precipitacao(int id, float valor, int posto) {
        this.id = id;
        this.valor = valor;
        this.data = new Data();
        this.posto = posto;
    }

    public Precipitacao() {
        this.id = 0;
        this.valor = 0;
        this.data = new Data();
        this.posto = 0;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public float getValor() {
        return valor;
    }
    public void setValor(float valor) {
        this.valor = valor;
    }

    public Data getData() {
        return data;
    }
    public void setData(Data data) {
        this.data = data;
    }

    public int getPosto() {
        return posto;
    }
    public void setPosto(int posto) {
        this.posto = posto;
    }

    @Override
    public String toString() {
        return "id:" + id + " valor:" + valor + " data:" + data.toString() + " posto:" + posto;
    }

    @Override
    public int hashCode() {
        // Retorna o hash code de data + 1M
        return this.getData().hashCode() + 1_000_000;
    }

    @Override
    public int compareTo(Precipitacao outra) {
        // Ordena pelo hash de Data
        return Integer.compare(this.data.hashCode(), outra.getData().hashCode());
    }
}
