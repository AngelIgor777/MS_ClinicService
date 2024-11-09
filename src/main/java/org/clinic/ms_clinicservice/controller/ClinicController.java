package org.clinic.ms_clinicservice.controller;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.clinic.ms_clinicservice.dto.ClinicDTO;
import org.clinic.ms_clinicservice.entity.Clinic;
import org.clinic.ms_clinicservice.exception.ErrorMessage;
import org.clinic.ms_clinicservice.request.ext.ClinicBasicRequest;
import org.clinic.ms_clinicservice.request.ext.ClinicUpdateRequest;
import org.clinic.ms_clinicservice.response.ext.ClinicBasicResponse;
import org.clinic.ms_clinicservice.response.ext.ClinicIdResponse;
import org.clinic.ms_clinicservice.service.impl.ClinicServiceImpl;
import org.clinic.ms_clinicservice.service.impl.MapServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/clinics")
@OpenAPIDefinition(info = @Info(title = "Взаимодействие с сущностью Клиники и её побочными сущностями",
        description = "Создание и последующие манипуляции профилем Клиники"))
@Tag(name = "Управление Клиниками")
public class ClinicController {

    private final ClinicServiceImpl clinicServiceImpl;
    private final MapServiceImpl mapService;



    @PostMapping
    @Operation(summary = "Создание профиля клиники",
            description = "Создаёт профиль клиники для последующего добавления адреса, контаков, медиков и докторов в профиль Клиники",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Успешно зарегистрировано",
                            content = @Content(schema = @Schema(implementation = ClinicIdResponse.class))),
                    @ApiResponse(responseCode = "409", description = "Уникальные данные уже зарегистрированы",
                            content = @Content(schema = @Schema(implementation = ErrorMessage.class))),
            })
    public ResponseEntity<ClinicIdResponse> createClinic(@RequestBody ClinicBasicRequest clinicBasicRequest) {
        Clinic clinic = mapService.mapToClinic(clinicBasicRequest);
        Clinic createdClinic = clinicServiceImpl.create(clinic);

        ClinicIdResponse idResponse = new ClinicIdResponse(createdClinic.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(idResponse);
    }



    @Operation(summary = "Получение профиля клиники",
            description = "Информации про клинику, айди Контактов, адресов, услуги и тд.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Успешно получено",
                            content = @Content(schema = @Schema(implementation = ClinicDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Данные не найдены",
                            content = @Content(schema = @Schema(implementation = ErrorMessage.class))),
            })
    @GetMapping("/{id}")
    public ResponseEntity<ClinicDTO> getClinicById(@PathVariable Integer id) {
        ClinicDTO clinic = clinicServiceImpl.getClinicById(id);
        return ResponseEntity.ok(clinic);
    }



    @Operation(summary = "Получение всех клиник",
            description = "Массив клиник.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Успешно получены все клиники",
                            content = @Content(schema = @Schema(implementation = ClinicDTO.class))),
            })
    @GetMapping
    public ResponseEntity<List<ClinicDTO>> getAllClinics() {
        List<ClinicDTO> clinics = clinicServiceImpl.getAllClinics();
        return ResponseEntity.ok(clinics);
    }



    @Operation(summary = "Обновление профиля клиники",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Успешно обновлено",
                            content = @Content(schema = @Schema(implementation = ClinicBasicResponse.class))),
            })
    @PutMapping("/{id}")
    public ResponseEntity<ClinicBasicResponse> updateClinic(@PathVariable Integer id, @RequestBody ClinicUpdateRequest clinicUpdateRequest) {
        Clinic clinic = mapService.mapToClinic(clinicUpdateRequest);
        Clinic updatedClinic = clinicServiceImpl.updateClinic(id, clinic);

        ClinicBasicResponse clinicBasicResponse = new ClinicBasicResponse(id,
                clinicUpdateRequest.getName(),
                clinicUpdateRequest.getDescription());
        return ResponseEntity.ok(clinicBasicResponse);
    }


    @Operation(summary = "Удаление профиля клиники",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Успешно удалено")})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClinic(@PathVariable Integer id) {
        clinicServiceImpl.deleteClinic(id);
        return ResponseEntity.noContent().build();
    }
}
