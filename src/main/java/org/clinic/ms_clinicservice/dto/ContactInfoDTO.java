package org.clinic.ms_clinicservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContactInfoDTO {
    private Integer id;
    private Integer clinicId;
    private String workPhone;
    private String workEmail;
}
