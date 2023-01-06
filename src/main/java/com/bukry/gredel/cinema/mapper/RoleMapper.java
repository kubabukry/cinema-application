package com.bukry.gredel.cinema.mapper;

import com.bukry.gredel.cinema.dto.RoleDto;
import com.bukry.gredel.cinema.model.Role;

import java.util.List;
import java.util.stream.Collectors;

public class RoleMapper {

    public static RoleDto mapRoleToRoleDto(Role role){
        return RoleDto.builder()
                .id(role.getId())
                .name(role.getName())
                .build();
    }

    public static List<RoleDto> mapRoleListToRoleDtoList(List<Role> roleList){
        return roleList.stream()
                .map(role -> RoleDto
                        .builder()
                        .id(role.getId())
                        .name(role.getName())
                        .build())
                .collect(Collectors.toList());
    }
}
