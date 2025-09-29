package org.example;

public class FipeResponse {
    private double valor;
    private String mes;

    public FipeResponse(double valor, String mes) {
        this.valor = valor;
        this.mes = mes;
    }

    public double getValor() { return valor; }
    public String getMes() { return mes; }
}
