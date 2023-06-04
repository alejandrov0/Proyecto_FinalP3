package com.proyectofinal.ProyectoAgenda.Controller;

import com.proyectofinal.ProyectoAgenda.Repo.ContactRepository;
import com.proyectofinal.ProyectoAgenda.model.Contacto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ContactoController {

    @Autowired
    private ContactRepository contactRepository;
    @GetMapping("")
    public String index(Model model) {
        List<Contacto> contactos = contactRepository.findAll();
        System.out.println("length"+contactos.size());
        model.addAttribute("contactos", contactos);
        return "index";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
    model.addAttribute("contacto", new Contacto());
    return "nuevo";

    }
    @PostMapping("/nuevo")

        public String Crear(@Validated Contacto contacto,
                            BindingResult bindingResult, Model model, RedirectAttributes ra) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("contacto", new Contacto());
            return "nuevo";
        }
        contactRepository.save(contacto);


        ra.addFlashAttribute("msgExito", "El Paciente ha sido creado satisfactoriamente");
        return "redirect:/";
    }
    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Integer id, Model model) {
        Contacto contacto = contactRepository.getReferenceById(id);
        model.addAttribute("contacto", contacto);
        return "nuevo";
    }
    @PostMapping("/{id}/editar")
    public String actualizar(
            @PathVariable Integer id,
            @Validated Contacto contacto,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes ra
    ) {
        Contacto contactoFromDB = contactRepository.getReferenceById(id);
        if (bindingResult.hasErrors()) {
            model.addAttribute("contacto", contactoFromDB);
            return "nuevo";
        }

        contactoFromDB.setNombre(contacto.getNombre());
        contactoFromDB.setCelular(contacto.getCelular());
        contactoFromDB.setEmail(contacto.getEmail());
        contactoFromDB.setFechadeNacimiento(contacto.getFechadeNacimiento());

        contactRepository.save(contactoFromDB);

        ra.addFlashAttribute("msgExito", "El Paciente ha sido actualizado satisfactoriamente");
        return "redirect:/";
    }
}



