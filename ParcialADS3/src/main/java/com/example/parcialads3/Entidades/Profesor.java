package com.example.parcialads3.Entidades;

import java.util.List;

public class Profesor extends  Empleado{
    private List<Asignatura> materias;
    private String Escalafon;
    private int salarioMinimo;

    public Profesor(String nombre, String ID, String dependencia, String cargo, int salariosM, List<Asignatura> materias, String escalafon, int salarioMinimo) {
        super(nombre, ID, dependencia, cargo, salariosM);
        this.materias = materias;
        this.salarioMinimo=salarioMinimo;
        Escalafon = escalafon;
    }

    public Profesor() {
        super();
    }

    public List<Asignatura> getMaterias() {
        return materias;
    }

    public void setMaterias(List<Asignatura> materias) {
        this.materias = materias;
    }

    public String getEscalafon() {
        return Escalafon;
    }

    public void setEscalafon(String escalafon) {
        Escalafon = escalafon;
    }

    public void agregarAsignatura(Asignatura asignatura) {
        materias.add(asignatura);
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString()).append("\n");
        sb.append("Escalafón: ").append(Escalafon).append("\n");
        sb.append("Asignaturas:\n");
        for (Asignatura asignatura : materias) {
            sb.append("- ").append(asignatura).append("\n");
        }
        return sb.toString();
    }
    public float calcularSalario() {
        float Salario;
        int horas=0;
        for(Asignatura a: materias){
            horas+=a.getHoras();
        }
        Salario= (float) (0.88*horas*super.getSalariosM()*salarioMinimo);
        return  Salario;
    }
}
