package org.clinic.ms_clinicservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClinicDTO {
    private Integer id;
    private String name;
    private String description;
    private String createdAt;
    private Integer addressId;
    private Integer contactId;
}