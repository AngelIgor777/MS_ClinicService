package org.clinic.ms_clinicservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressDTO {
    private Integer Id;
    private Integer clinicId;
    private String city;
    private String street;
    private String buildingNumber;
}
