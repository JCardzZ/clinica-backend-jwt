package com.mm.com.security.controller;

import com.mm.com.crud.dto.MascotaDto;
import com.mm.com.crud.dto.MessageDto;
import com.mm.com.crud.entity.MascotaEntity;
import com.mm.com.crud.util.AttributeException;
import com.mm.com.crud.util.ResourceNotFoundException;
import com.mm.com.security.dto.CreateUserDto;
import com.mm.com.security.dto.JwtTokenDto;
import com.mm.com.security.dto.LoginUserDto;
import com.mm.com.security.entity.UserEntity;
import com.mm.com.security.service.UserEntityService;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    UserEntityService userEntityService;

    @PostMapping("/create")
    public ResponseEntity<MessageDto> create(@Valid @RequestBody CreateUserDto dto) throws AttributeException {
        UserEntity userEntity = userEntityService.create(dto);
        return ResponseEntity.ok(new MessageDto(HttpStatus.OK, "¡Usuario " + userEntity.getUsername() + " creado con éxito!"));
    }

    @PostMapping("/create-admin")
    public ResponseEntity<MessageDto> createAdmin(@Valid @RequestBody CreateUserDto dto) throws AttributeException {
        UserEntity userEntity = userEntityService.createAdmin(dto);
        return ResponseEntity.ok(new MessageDto(HttpStatus.OK, "¡Administrador " + userEntity.getUsername() + " creado con éxito!"));
    }

    @PostMapping("/create-user")
    public ResponseEntity<MessageDto> createUser(@Valid @RequestBody CreateUserDto dto) throws AttributeException {
        UserEntity userEntity = userEntityService.createUser(dto);
        return ResponseEntity.ok(new MessageDto(HttpStatus.OK, "¡Usuario " + userEntity.getUsername() + " creado con éxito!"));
    }

    @PostMapping("/login")
    public ResponseEntity<JwtTokenDto> login(@Valid @RequestBody LoginUserDto dto) throws AttributeException {
        JwtTokenDto jwtTokenDto = userEntityService.login(dto);
        return ResponseEntity.ok(jwtTokenDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageDto> delete(@PathVariable("id") int id) throws ResourceNotFoundException {
        log.info("<<<ELIMINANDO USUARIO DE LA BD>>>");
        UserEntity register = userEntityService.delete(id);
        String message = "Usuario eliminada " + register.getUsername() + " ha sido eliminado";
        return ResponseEntity.ok(new MessageDto(HttpStatus.OK, message));
    }

    @GetMapping
    public ResponseEntity<List<UserEntity>> getAllRegister() {
        log.info("<<<CONSULTANDO USUARIOS DE LA BD>>>");
        return ResponseEntity.ok(userEntityService.getAllRegister());

    }
    @PutMapping("/{id}")
    public ResponseEntity<MessageDto> save(@Valid @PathVariable("id") int id, @Valid @RequestBody CreateUserDto dto) throws ResourceNotFoundException, AttributeException {
        log.info("<<<ACTUALIZANDO USUARIOS DE LA BD>>>");
        UserEntity register = userEntityService.updateRegister(id, dto);
        String message = "Usuario " + register.getUsername() + " ha sido actualizado";
        return ResponseEntity.ok(new MessageDto(HttpStatus.OK, message));
    }
}
