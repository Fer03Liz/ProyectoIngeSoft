package com.example.parcialads3;

import com.example.parcialads3.Entidades.Empleado;

import java.util.List;

@XmlRootElement(name = "Nomina")
public class Nomina {

    private List<Empleado> empleados;

    @XmlElement(name = "Empleado")
    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
    }
}

