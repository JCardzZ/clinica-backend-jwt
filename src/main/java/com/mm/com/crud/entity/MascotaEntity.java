package com.mm.com.crud.entity;

import com.mm.com.global.entity.EntityId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "mascota")
public class MascotaEntity extends EntityId {
    private String fechaIngreso;
    private String nombreMascota;
    private String propietario;
    private String dui;
    private String direccion;

    public MascotaEntity() {
    }

    public MascotaEntity(int id, String fechaIngreso, String nombreMascota, String propietario, String dui, String direccion) {
        this.id = id;
        this.fechaIngreso = fechaIngreso;
        this.nombreMascota = nombreMascota;
        this.propietario = propietario;
        this.dui = dui;
        this.direccion = direccion;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getNombreMascota() {
        return nombreMascota;
    }

    public void setNombreMascota(String nombreMascota) {
        this.nombreMascota = nombreMascota;
    }

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }

    public String getDui() {
        return dui;
    }

    public void setDui(String dui) {
        this.dui = dui;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}

