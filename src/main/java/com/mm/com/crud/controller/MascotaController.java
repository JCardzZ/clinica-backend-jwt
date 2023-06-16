package com.mm.com.crud.controller;

import com.mm.com.crud.dto.MascotaDto;
import com.mm.com.crud.dto.MessageDto;
import com.mm.com.crud.entity.MascotaEntity;
import com.mm.com.crud.service.MascotaService;
import com.mm.com.crud.util.AttributeException;
import com.mm.com.crud.util.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/register")
@CrossOrigin(origins = "http://localhost:4200")
public class MascotaController {

    @Autowired
    MascotaService mascotaService;
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping
    public ResponseEntity<List<MascotaEntity>> getAllRegister() {
        log.info("<<<CONSULTANDO REGISTROS DE LA BD>>>");
        return ResponseEntity.ok(mascotaService.getAllRegister());

    }
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("/{id}")
    public ResponseEntity<MascotaEntity> getOneRegister(@PathVariable("id") int id) throws ResourceNotFoundException {
        log.info("<<<CONSULTANDO REGISTRO POR ID DE LA BD>>>");
        return ResponseEntity.ok(mascotaService.getOneRegister(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<MessageDto> saveRegister(@Valid @RequestBody MascotaDto dto) throws AttributeException {
        log.info("<<<CREANDO REGISTRO EN LA BD>>>.");
        MascotaEntity register = mascotaService.saveRegister(dto);
        String message = "Mascota " + register.getNombreMascota() + " ha sido almacenado con éxito";
        return ResponseEntity.ok(new MessageDto(HttpStatus.OK, message));
    }


    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<MessageDto> save(@Valid @PathVariable("id") int id, @Valid @RequestBody MascotaDto dto) throws ResourceNotFoundException, AttributeException {
        log.info("<<<ACTUALIZANDO REGISTROS DE LA BD>>>");
        MascotaEntity register = mascotaService.updateRegister(id, dto);
        String message = "Mascota " + register.getNombreMascota() + " ha sido actualizado";
        return ResponseEntity.ok(new MessageDto(HttpStatus.OK, message));
    }
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<MessageDto> delete(@PathVariable("id") int id) throws ResourceNotFoundException {
        log.info("<<<ELIMINANDO REGISTRO DE LA BD>>>");
        MascotaEntity register = mascotaService.delete(id);
        String message = "Mascota eliminada " + register.getNombreMascota() + " ha sido eliminado";
        return ResponseEntity.ok(new MessageDto(HttpStatus.OK, message));
    }
}
