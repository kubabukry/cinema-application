package com.bukry.gredel.cinema.controller;

import com.bukry.gredel.cinema.dto.*;
import com.bukry.gredel.cinema.exception.ErrorResponse;
import com.bukry.gredel.cinema.service.PersonService;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bukry.gredel.cinema.mapper.PersonMapper.mapPersonListToPersonDtoList;
import static com.bukry.gredel.cinema.mapper.PersonMapper.mapPersonToPersonDto;

@RestController
@Tag(name = "users")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/persons/auth/register")
    @Operation(method = "POST", summary = "Method used for registration" ,description = "Method used for registration to be exposed without security interference, generates JWT token with code 200",
    externalDocs = @ExternalDocumentation(url = "https://jwt.io/", description = "Read more about JWT tokens"), responses = {
            @ApiResponse(responseCode = "200", description = "User provided valid login, password and email. User has been saved to database and has the token generated in response",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = AuthenticationResponseDto.class))}),
            @ApiResponse(responseCode = "400", description = "User provided invalid login, password or email. No data persisted to database",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "409", description = "User provided login or email which already exists. No data persisted to database",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))})
    })
    public ResponseEntity<AuthenticationResponseDto> register(
            @Valid @RequestBody PersonCreationDto personCreationDto){
        return ResponseEntity.ok(personService.createPerson(personCreationDto));
    }

    @PostMapping("/persons/auth/authenticate")
    @Operation(method = "POST", summary = "Method used for authentication" ,description = "Method used for authentication to be exposed without security interference, generates JWT token with code 200",
            externalDocs = @ExternalDocumentation(url = "https://jwt.io/", description = "Read more about JWT tokens"), responses = {
            @ApiResponse(responseCode = "200", description = "User provided correct credentials, JWT token generated in response",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = AuthenticationResponseDto.class))}),
            @ApiResponse(responseCode = "400", description = "User provided wrong login",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "403", description = "User provided wrong credentials, no error message applied",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))})
    })
    public ResponseEntity<AuthenticationResponseDto> authenticate(
            @RequestBody AuthenticationRequest authenticationRequest){
        return ResponseEntity.ok(personService.authenticate(authenticationRequest));
    }

    @GetMapping("/persons/single/{id}")
    @SecurityRequirement(name = "bearer")
    public PersonDto getSinglePerson(
            @Parameter(description = "unique id of searched user")
            @PathVariable Long id){
        return mapPersonToPersonDto(personService.getSinglePerson(id));
    }

    @GetMapping("/persons/all")
    @SecurityRequirement(name = "bearer")
    public List<PersonDto> getPersons(){
        return mapPersonListToPersonDtoList(personService.getPersons());
    }

    @PutMapping("/persons/update/{id}")
    @SecurityRequirement(name = "bearer")
    public void updatePerson(
            @Valid
            @PathVariable @Parameter(description = "id of user to be updated")
            Long id, @RequestBody PersonUpdateDto personUpdateDto){
        personService.updatePerson(id, personUpdateDto);
    }

    @PutMapping("/persons/change-password")
    @Operation(method = "PUT", summary = "Method used for password change" ,description = "Method used for password change, JWT token needed for authorization",
            externalDocs = @ExternalDocumentation(url = "https://jwt.io/", description = "Read more about JWT tokens"), responses = {
            @ApiResponse(responseCode = "200", description = "User provided correct login, both passwords match. User has been updated in database",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = AuthenticationResponseDto.class))}),
            @ApiResponse(responseCode = "400", description = "User provided invalid login or one of two passwords. No data persisted to database",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "403", description = "User not authorized with correct token. No data persisted to database",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))})
    })
    @SecurityRequirement(name = "bearer")
    public void changePassword(@Valid @RequestBody ChangePasswordDto changePasswordDto){
        personService.changePassword(changePasswordDto);
    }

    @DeleteMapping("/persons/delete/{id}")
    @SecurityRequirement(name = "bearer")
    public void deletePerson(
            @Parameter(description = "unique id of user to be deleted")
            @PathVariable Long id){
        personService.deletePerson(id);
    }
}
