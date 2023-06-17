package com.mm.com.crud.service;

import com.mm.com.crud.dto.MascotaDto;
import com.mm.com.crud.entity.MascotaEntity;
import com.mm.com.crud.repositoy.MascotaRepository;
import com.mm.com.crud.util.AttributeException;
import com.mm.com.crud.util.ResourceNotFoundException;
import com.mm.com.global.util.Operations;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class MascotaService {

    @Autowired
    private MascotaRepository mascotaRepository;

    public List<MascotaEntity> getAllRegister(){
        return mascotaRepository.findAll();
    }
    public MascotaEntity getOneRegister(int id) throws ResourceNotFoundException {
        return mascotaRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Registro No Encontrado"));
    }

    public MascotaEntity saveRegister(MascotaDto dto) throws AttributeException {
        if (mascotaRepository.existsByDui(dto.getDui()))
            throw new AttributeException("Numero de DUI ya esta en uso");
            int id = Operations.autoIncrement(mascotaRepository.findAll());
        MascotaEntity register = new MascotaEntity(id,dto.getFechaIngreso(), dto.getNombreMascota(),dto.getPropietario(), dto.getDui(),  dto.getDireccion());
        return mascotaRepository.save(register);
    }

    public MascotaEntity updateRegister(int id, MascotaDto dto) throws ResourceNotFoundException, AttributeException {
        MascotaEntity register = mascotaRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Registro No Encontrado"));
        if (mascotaRepository.existsByDui(dto.getDui()) && mascotaRepository.findByDui(dto.getDui()).get().getId() != id)
            throw new AttributeException("Numero de DUI ya esta en uso");
        register.setFechaIngreso(dto.getFechaIngreso());
        register.setNombreMascota(dto.getNombreMascota());
        register.setPropietario(dto.getPropietario());
        register.setDui(dto.getDui());
        register.setDireccion(dto.getDireccion());
        return mascotaRepository.save(register);
    }
    public MascotaEntity delete(int id) throws ResourceNotFoundException {
        MascotaEntity register = mascotaRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Registro No Encontrado"));
        mascotaRepository.delete(register);
        return register;
    }

}
