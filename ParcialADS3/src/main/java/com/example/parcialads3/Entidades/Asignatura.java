package com.example.parcialads3.Entidades;

public class Asignatura {
    private int Horas;
    private String nombre;

    public Asignatura(int horas, String nombre) {
        Horas = horas;
        this.nombre = nombre;
    }

    public Asignatura() {

    }

    public int getHoras() {
        return Horas;
    }

    public void setHoras(int horas) {
        Horas = horas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + ", Horas: " + Horas;
    }
}
