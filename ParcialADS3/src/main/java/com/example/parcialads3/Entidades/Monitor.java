package com.example.parcialads3.Entidades;

import java.util.List;

public class Monitor extends  Empleado{
    private List<Asignatura> materias;

    public Monitor(String nombre, String ID, String dependencia, String cargo, int salariosM, List<Asignatura> materias) {
        super(nombre, ID, dependencia, cargo, salariosM);
        this.materias = materias;
    }

    public Monitor() {
        super();
    }

    public List<Asignatura> getMaterias() {
        return materias;
    }

    public void setMaterias(List<Asignatura> materias) {
        this.materias = materias;
    }

    public void agregarAsignatura(Asignatura asignatura) {
        materias.add(asignatura);
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString()).append("\n");
        sb.append("Asignaturas:\n");
        for (Asignatura asignatura : materias) {
            sb.append("- ").append(asignatura).append("\n");
        }
        return sb.toString();
    }

    @Override
    public float calcularSalario() {
        float Salario;
        int horas=0;
        for(Asignatura a: materias){
            horas+=a.getHoras();
        }
        Salario=horas*super.getSalariosM();
        return  Salario;
    }

}
