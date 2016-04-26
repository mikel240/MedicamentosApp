package com.example.atmosfera.medicamentosapp.pojo;

public class Medicamento {

    private int idMedicamento;
    private String nombre;
    private int forma; //0=Pastillas 1=Inyeccion 2=Jarabe
    private String via;
    private String horaPrimeraIngesta;
    private String fechaInicioToma;
    private int duracion;
    private int intervalo;

    public Medicamento() {

    }

    public Medicamento(int idMedicamento, String nombre, int forma, String via, String horaPrimeraIngesta, String fechaInicioToma, int duracion, int intervalo) {
        this.idMedicamento = idMedicamento;
        this.nombre = nombre;
        this.forma = forma;
        this.via = via;
        this.horaPrimeraIngesta = horaPrimeraIngesta;
        this.fechaInicioToma = fechaInicioToma;
        this.duracion = duracion;
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

    public int getForma() {
        return forma;
    }

    public void setForma(int forma) {
        this.forma = forma;
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public String getHoraPrimeraIngesta() {
        return horaPrimeraIngesta;
    }

    public void setHoraPrimeraIngesta(String horaPrimeraIngesta) {
        this.horaPrimeraIngesta = horaPrimeraIngesta;
    }

    public String getFechaInicioToma() {
        return fechaInicioToma;
    }

    public void setFechaInicioToma(String fechaInicioToma) {
        this.fechaInicioToma = fechaInicioToma;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public int getIntervalo() {
        return intervalo;
    }

    public void setIntervalo(int intervalo) {
        this.intervalo = intervalo;
    }
}
