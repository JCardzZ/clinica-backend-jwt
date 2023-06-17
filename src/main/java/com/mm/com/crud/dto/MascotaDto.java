package com.mm.com.crud.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class MascotaDto {
    @NotBlank(message = "Se debe agregar una fecha")
    private String fechaIngreso;
    @NotBlank(message = "Es obligatorio ingresar el nombre de la mascota")
    private String nombreMascota;
    @NotBlank(message = "Es obligatorio ingresar el nombre del propietario")
    private String propietario;
    private String dui;
    @NotBlank(message = "Es obligatorio ingresar una direccion")
    private String direccion;
}
