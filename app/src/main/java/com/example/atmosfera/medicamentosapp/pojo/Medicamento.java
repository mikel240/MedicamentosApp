package com.example.atmosfera.medicamentosapp.pojo;

import java.util.ArrayList;

public class Medicamento {

    private int idMedicamento;
    private String nombre;
    private int forma; //0=Pastillas 1=Inyeccion 2=Jarabe
    private String via;
    private String horaPrimeraIngesta;
    private String fechaInicioIngesta;
    private int duracion;
    private int intervalo;
    private ArrayList<Aviso> listaAvisos;

    public Medicamento() {
        this.listaAvisos = new ArrayList<>();
    }

    public Medicamento(int idMedicamento, String nombre, int forma, String via, String horaPrimeraIngesta, String fechaInicioIngesta, int duracion, int intervalo) {
        this.idMedicamento = idMedicamento;
        this.nombre = nombre;
        this.forma = forma;
        this.via = via;
        this.horaPrimeraIngesta = horaPrimeraIngesta;
        this.fechaInicioIngesta = fechaInicioIngesta;
        this.duracion = duracion;
        this.intervalo = intervalo;
        this.listaAvisos = new ArrayList<>();
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

    public String getFechaInicioIngesta() {
        return fechaInicioIngesta;
    }

    public void setFechaInicioIngesta(String fechaInicioIngesta) {
        this.fechaInicioIngesta = fechaInicioIngesta;
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

    public void setListaAvisos(ArrayList<Aviso> listaAvisos) {
        this.listaAvisos = listaAvisos;
    }

    public ArrayList<Aviso> getListaAvisos() {
        return this.listaAvisos;
    }

    public void addAviso(Aviso aviso) {
        this.listaAvisos.add(aviso);
    }

    public void addListaAvisos(ArrayList<Aviso> listaAvisos) {
        this.listaAvisos.addAll(listaAvisos);
    }
}
