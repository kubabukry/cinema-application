package com.bukry.gredel.cinema.service;

import com.bukry.gredel.cinema.dto.RoleNameDto;
import com.bukry.gredel.cinema.exception.NoSuchRoleExistsException;
import com.bukry.gredel.cinema.exception.RoleAlreadyExistsException;
import com.bukry.gredel.cinema.exception.RoleIsInUseException;
import com.bukry.gredel.cinema.model.Person;
import com.bukry.gredel.cinema.model.Role;
import com.bukry.gredel.cinema.repository.PersonRepository;
import com.bukry.gredel.cinema.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    private final PersonRepository personRepository;

    public RoleService(RoleRepository roleRepository, PersonService personService, PersonRepository personRepository) {
        this.roleRepository = roleRepository;
        this.personRepository = personRepository;
    }

    public Role createRole(RoleNameDto roleNameDto){
        Boolean roleExists = roleRepository.existsByName(roleNameDto.getName());
        if(!roleExists){
            Role role = new Role();
            role.setName(roleNameDto.getName());
            return roleRepository.save(role);
        } else
            throw new RoleAlreadyExistsException("Role already exists by name: "+ roleNameDto.getName());
    }

    public List<Role> getRoles(){
        return roleRepository.findAll();
    }

    public Role getSingleRole(Long id){
        return roleRepository.findById(id)
                .orElseThrow(() -> new NoSuchRoleExistsException("No such role exists with id: "+id));
    }

    public Role updateRole(Long id, RoleNameDto roleNameDto) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new NoSuchRoleExistsException("No such role exists with id: "+id));
        Boolean nameExists = roleRepository.existsByName(roleNameDto.getName());
        if(!nameExists){
            role.setName(roleNameDto.getName());
        } else {
            throw new RoleAlreadyExistsException("Role already exists by name: "+ roleNameDto.getName());
        }
        return roleRepository.save(role);
    }

    //todo ma sprawdzać czy rola jest w użyciu
    public void deleteRole(Long id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new NoSuchRoleExistsException("No such role exists with id: "+id));
        if(roleInUse(id))
            throw new RoleIsInUseException("Role with id: "+id+" is in use");
        roleRepository.delete(role);
    }

    private Boolean roleInUse(Long id){
        Set<Person> personsUsingRole = personRepository.findAll()
                .stream()
                .filter(person -> person.getRole().getId() == id)
                .collect(Collectors.toSet());
        return !personsUsingRole.isEmpty();
    }
    //setRoleToUser
}
