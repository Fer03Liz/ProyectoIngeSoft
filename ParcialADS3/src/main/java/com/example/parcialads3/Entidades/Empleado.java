package com.example.parcialads3.Entidades;

public class Empleado {
    private String Nombre;
    private String ID;
    private String Dependencia;
    private String Cargo;
    private int SalariosM;

    public Empleado(String nombre, String ID, String dependencia, String cargo, int salariosM) {
        Nombre = nombre;
        this.ID = ID;
        Dependencia = dependencia;
        Cargo = cargo;
        SalariosM = salariosM;
    }

    public Empleado() {

    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getDependencia() {
        return Dependencia;
    }

    public void setDependencia(String dependencia) {
        Dependencia = dependencia;
    }

    public String getCargo() {
        return Cargo;
    }

    public void setCargo(String cargo) {
        Cargo = cargo;
    }

    public int getSalariosM() {
        return SalariosM;
    }

    public void setSalariosM(int salariosM) {
        SalariosM = salariosM;
    }

    @Override
    public String toString() {
        // Devuelve la información que deseas mostrar en la ListView
        return "Nombre: " + this.Nombre + ", ID: " + this.ID + ", Cargo: " + this.Cargo;
    }

    public float calcularSalario() {
        float Salario;
        Salario= (float) (0.88*SalariosM*900000);
        return  Salario;
    }
}
