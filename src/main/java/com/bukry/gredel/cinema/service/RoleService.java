package com.bukry.gredel.cinema.service;

import com.bukry.gredel.cinema.dto.RoleNameDto;
import com.bukry.gredel.cinema.exception.NoSuchRoleExists;
import com.bukry.gredel.cinema.exception.RoleAlreadyExists;
import com.bukry.gredel.cinema.model.Role;
import com.bukry.gredel.cinema.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role createRole(RoleNameDto roleNameDto){
        Boolean roleExists = roleRepository.existsByName(roleNameDto.getName());
        if(!roleExists){
            Role role = new Role();
            role.setName(roleNameDto.getName());
            return roleRepository.save(role);
        } else
            throw new RoleAlreadyExists("Role already exists by name: "+ roleNameDto.getName());
    }

    public List<Role> getRoles(){
        return roleRepository.findAll();
    }

    public Role getSingleRole(Long id){
        return roleRepository.findById(id)
                .orElseThrow(() -> new NoSuchRoleExists("No such role exists with id: "+id));
    }

    public Role updateRole(Long id, RoleNameDto roleNameDto) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new NoSuchRoleExists("No such role exists with id: "+id));
        Boolean nameExists = roleRepository.existsByName(roleNameDto.getName());
        if(!nameExists){
            role.setName(roleNameDto.getName());
        } else {
            throw new RoleAlreadyExists("Role already exists by name: "+ roleNameDto.getName());
        }
        return roleRepository.save(role);
    }

    //todo ma sprawdzać czy rola jest w użyciu
//    public void deleteRole(Long id) {
//
//    }
    //setRoleToUser
}
