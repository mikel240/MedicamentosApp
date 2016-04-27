package com.example.atmosfera.medicamentosapp.pojo;

public class Aviso {
    private int idAviso;
    private int idMedicamento;
    private String fechaAviso;
    private String horaAviso;
    private boolean tomado;
    private Medicamento medicamento;

    public Aviso() {

    }

    public Aviso(int idAviso, int idMedicamento, String fechaAviso, String horaAviso, boolean tomado) {
        this.idAviso = idAviso;
        this.idMedicamento = idMedicamento;
        this.fechaAviso = fechaAviso;
        this.horaAviso = horaAviso;
        this.tomado = tomado;
    }

    public int getIdAviso() {
        return idAviso;
    }

    public void setIdAviso(int idAviso) {
        this.idAviso = idAviso;
    }

    public int getIdMedicamento() {
        return idMedicamento;
    }

    public void setIdMedicamento(int idMedicamento) {
        this.idMedicamento = idMedicamento;
    }

    public String getFechaAviso() {
        return fechaAviso;
    }

    public void setFechaAviso(String fechaAviso) {
        this.fechaAviso = fechaAviso;
    }

    public String getHoraAviso() {
        return horaAviso;
    }

    public void setHoraAviso(String horaAviso) {
        this.horaAviso = horaAviso;
    }

    public boolean isTomado() {
        return tomado;
    }

    public void setTomado(boolean tomado) {
        this.tomado = tomado;
    }

    public Medicamento getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(Medicamento medicamento) {
        this.medicamento = medicamento;
    }
}
