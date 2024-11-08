package org.clinic.ms_clinicservice.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public abstract class ClinicRequest {
    protected String name;
    protected String description;
}
