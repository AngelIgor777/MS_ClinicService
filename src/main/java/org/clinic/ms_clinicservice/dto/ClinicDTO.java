package org.clinic.ms_clinicservice.dto;


import lombok.Data;
import org.clinic.ms_clinicservice.response.ClinicResponse;

@Data
public class ClinicDTO extends ClinicResponse {
    private String name;
    private String description;
    private String createdAt;
    private Integer addressId;
    private Integer contactId;

    public ClinicDTO(Integer id, String name, String description, String createdAt, Integer contactId, Integer addressId) {
        super(id);
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
        this.contactId = contactId;
        this.addressId = addressId;
    }
}