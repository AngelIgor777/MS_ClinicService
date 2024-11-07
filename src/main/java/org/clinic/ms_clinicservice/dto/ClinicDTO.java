package org.clinic.ms_clinicservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClinicDTO {
    private Integer id;
    private String name;
    private String description;
    private String createdAt;
    private Integer addressId;
    private Integer contactId;
}