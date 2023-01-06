package com.bukry.gredel.cinema.controller;

import com.bukry.gredel.cinema.dto.RoleNameDto;
import com.bukry.gredel.cinema.dto.RoleDto;
import com.bukry.gredel.cinema.service.RoleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bukry.gredel.cinema.mapper.RoleMapper.mapRoleToRoleDto;
import static com.bukry.gredel.cinema.mapper.RoleMapper.mapRoleListToRoleDtoList;


@RestController
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping("/roles")
    public RoleDto createRole(@RequestBody RoleNameDto roleNameDto){
        return mapRoleToRoleDto(roleService.createRole(roleNameDto));
    }

    @GetMapping("/roles")
    public List<RoleDto> getRoles(){
        return mapRoleListToRoleDtoList(roleService.getRoles());
    }

    @GetMapping("/roles/{id}")
    public RoleDto getSingleRole(@PathVariable Long id){
        return mapRoleToRoleDto(roleService.getSingleRole(id));
    }

    @PutMapping("roles/{id}")
    public void updateRole(@PathVariable Long id, @RequestBody RoleNameDto roleNameDto){
        roleService.updateRole(id, roleNameDto);
    }
//    @DeleteMapping("/roles/{id}")
//    public void deleteRole(@PathVariable Long id){
//        roleService.deleteRole(id);
//    }

}
