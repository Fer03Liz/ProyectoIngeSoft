package org.example.demo3.Entidades;

public class Bar {
    private String nombre;
    private String descripcion;
    private String ubicacion;
    private String horarioApertura;
    private String horarioCierre;
    private String tipoMusica;
    private int capacidadAforo;

    public Bar(String nombre,String descripcion, String ubicacion,String horarioApertura, String horarioCierre, String tipoMusica, int capacidadAforo){
        this.nombre=nombre;
        this.descripcion=descripcion;
        this.ubicacion=ubicacion;
        this.horarioApertura=horarioApertura;
        this.horarioCierre=horarioCierre;
        this.tipoMusica=tipoMusica;
        this.capacidadAforo=capacidadAforo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getHorarioApertura() {
        return horarioApertura;
    }

    public void setHorarioApertura(String horarioApertura) {
        this.horarioApertura = horarioApertura;
    }

    public String getHorarioCierre() {
        return horarioCierre;
    }

    public void setHorarioCierre(String horarioCierre) {
        this.horarioCierre = horarioCierre;
    }

    public String getTipoMusica() {
        return tipoMusica;
    }

    public void setTipoMusica(String tipoMusica) {
        this.tipoMusica = tipoMusica;
    }

    public int getCapacidadAforo() {
        return capacidadAforo;
    }

    public void setCapacidadAforo(int capacidadAforo) {
        this.capacidadAforo = capacidadAforo;
    }
}