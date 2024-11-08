package org.clinic.ms_clinicservice.controller;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.clinic.ms_clinicservice.dto.ClinicDTO;
import org.clinic.ms_clinicservice.entity.Clinic;
import org.clinic.ms_clinicservice.exception.ErrorMessage;
import org.clinic.ms_clinicservice.service.impl.ClinicServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/clinics")
@OpenAPIDefinition(info = @Info(title = "Взаимодействие с сущностью Клиники и её побочными сущностями",
        description = "Создание и последующие манипуляции профилем Клиники для получения данных"))
@Tag(name = "Управление Клиниками")
public class ClinicController {

    private final ClinicServiceImpl clinicServiceImpl;


    @PostMapping
    @Operation(summary = "Создание профиля клиники",
            description = "Создаёт профиль клиники для последующего добавления медиков и докторов",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Успешно зарегистрировано",
                            content = @Content(examples = @ExampleObject(value = "{\"id\" : \"23\"}"))),
                    @ApiResponse(responseCode = "409", description = "Уникальные данные уже зарегистрированы",
                            content = @Content(schema = @Schema(implementation = ErrorMessage.class))),
            })
    public ResponseEntity<HashMap<String, Integer>> createClinic(@RequestBody Clinic clinic) {
        Clinic createdClinic = clinicServiceImpl.createClinic(clinic);
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("id", createdClinic.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(hashMap);
    }


    @Operation(summary = "Получение профиля клиники",
            description = "Получение информации про клинику. Контакты, услуги  и тд.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Успешно получено",
                            content = @Content(schema = @Schema(implementation = Clinic.class))),
                    @ApiResponse(responseCode = "404", description = "Данные не найдены",
                            content = @Content(schema = @Schema(implementation = ErrorMessage.class))),
            })
    @GetMapping("/{id}")
    public ResponseEntity<ClinicDTO> getClinicById(@PathVariable Integer id) {
        ClinicDTO clinic = clinicServiceImpl.getClinicById(id);
        return ResponseEntity.ok(clinic);
    }


    @Operation(summary = "Получение всех клиник",
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
                            content = @Content(schema = @Schema(implementation = Clinic.class))),
            })
    @PutMapping("/{id}")
    public ResponseEntity<HashMap<String, Integer>> updateClinic(@PathVariable Integer id, @RequestBody Clinic clinic) {
        Clinic updatedClinic = clinicServiceImpl.updateClinic(id, clinic);
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("id", updatedClinic.getId());
        return ResponseEntity.ok(hashMap);
    }

    @Operation(summary = "Удаление профиля клиники",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Успешно удалено",
                            content = @Content(schema = @Schema(implementation = Clinic.class))),
            })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClinic(@PathVariable Integer id) {
        clinicServiceImpl.deleteClinic(id);
        return ResponseEntity.noContent().build();
    }
}
