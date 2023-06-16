package com.mm.com.security.service;


import com.mm.com.crud.util.AttributeException;
import com.mm.com.global.util.Operations;
import com.mm.com.security.dto.CreateUserDto;
import com.mm.com.security.dto.JwtTokenDto;
import com.mm.com.security.dto.LoginUserDto;
import com.mm.com.security.entity.UserEntity;
import com.mm.com.security.enums.RoleEnum;
import com.mm.com.security.jwt.JwtProvider;
import com.mm.com.security.repository.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserEntityService {

    @Autowired
    UserEntityRepository userEntityRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    AuthenticationManager authenticationManager;


    public UserEntity create(CreateUserDto dto) throws AttributeException {
        if(userEntityRepository.existsByUsername(dto.getUsername()))
            throw new AttributeException("Usuario ya esta en uso, crea uno nuevo");
        if(userEntityRepository.existsByEmail(dto.getEmail()))
            throw new AttributeException("Email ya esta en uso, crea uno nuevo");
        if(dto.getRoles().isEmpty())
            throw new AttributeException("Roles son obligatorios");
        return userEntityRepository.save(mapUserFromDto(dto));
    }

    public UserEntity createAdmin(CreateUserDto dto) throws AttributeException {
        if(userEntityRepository.existsByUsername(dto.getUsername()))
            throw new AttributeException("Usuario ya esta en uso, crea uno nuevo");
        if(userEntityRepository.existsByEmail(dto.getEmail()))
            throw new AttributeException("Email ya esta en uso, crea uno nuevo");
        List<String> roles = Arrays.asList("ROLE_ADMIN", "ROLE_USER");
        dto.setRoles(roles);
        return userEntityRepository.save(mapUserFromDto(dto));
    }

    public UserEntity createUser(CreateUserDto dto) throws AttributeException {
        if(userEntityRepository.existsByUsername(dto.getUsername()))
            throw new AttributeException("Usuario ya esta en uso, crea uno nuevo");
        if(userEntityRepository.existsByEmail(dto.getEmail()))
            throw new AttributeException("Email ya esta en uso, crea uno nuevoe");
        List<String> roles = Arrays.asList("ROLE_USER");
        dto.setRoles(roles);
        return userEntityRepository.save(mapUserFromDto(dto));
    }

    public JwtTokenDto login(LoginUserDto dto) {
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.generateToken(authentication);
        return new JwtTokenDto(token);
    }

    private UserEntity mapUserFromDto(CreateUserDto dto) {
        int id = Operations.autoIncrement(userEntityRepository.findAll());
        String password = passwordEncoder.encode(dto.getPassword());
        List<RoleEnum> roles =
                dto.getRoles().stream().map(rol -> RoleEnum.valueOf(rol)).collect(Collectors.toList());
        return new UserEntity(id, dto.getUsername(), dto.getEmail(), password, roles);
    }
}
