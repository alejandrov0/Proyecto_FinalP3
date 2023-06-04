package com.proyectofinal.ProyectoAgenda.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
public class Contacto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank
    private String nombre;
    @Email
    @NotEmpty
    private String email;
    @NotBlank
    private String celular;
    @PastOrPresent
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull
    private LocalDate FechadeNacimiento;
    private LocalDateTime FechadeRegistro;

    @PrePersist
    void asignarFechaActual() {
        this.FechadeRegistro = LocalDateTime.now();
    }
}

