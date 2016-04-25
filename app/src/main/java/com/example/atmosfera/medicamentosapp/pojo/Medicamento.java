package com.example.atmosfera.medicamentosapp.pojo;

public class Medicamento {

    private int idMedicamento;
    private String nombre;
    private String forma;
    private String color;
    private String fechaInicioToma;
    private String fechaFinToma;
    private int intervalo;

    public Medicamento() {

    }

    public Medicamento(int idMedicamento, String nombre, String forma, String color, String fechaInicioToma, String fechaFinToma, int intervalo) {
        this.idMedicamento = idMedicamento;
        this.nombre = nombre;
        this.forma = forma;
        this.color = color;
        this.fechaInicioToma = fechaInicioToma;
        this.fechaFinToma = fechaFinToma;
        this.intervalo = intervalo;
    }

    public int getIdMedicamento() {
        return idMedicamento;
    }

    public void setIdMedicamento(int idMedicamento) {
        this.idMedicamento = idMedicamento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getForma() {
        return forma;
    }

    public void setForma(String forma) {
        this.forma = forma;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getFechaInicioToma() {
        return fechaInicioToma;
    }

    public void setFechaInicioToma(String fechaInicioToma) {
        this.fechaInicioToma = fechaInicioToma;
    }

    public String getFechaFinToma() {
        return fechaFinToma;
    }

    public void setFechaFinToma(String fechaFinToma) {
        this.fechaFinToma = fechaFinToma;
    }

    public int getIntervalo() {
        return intervalo;
    }

    public void setIntervalo(int intervalo) {
        this.intervalo = intervalo;
    }
}
