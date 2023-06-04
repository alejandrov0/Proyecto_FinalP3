package com.proyectofinal.ProyectoAgenda.Repo;

import com.proyectofinal.ProyectoAgenda.model.Contacto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contacto,Integer> {

}