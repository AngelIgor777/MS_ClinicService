package org.clinic.ms_clinicservice.response.ext;

import lombok.Data;
import org.clinic.ms_clinicservice.response.ClinicResponse;

@Data
public class ClinicBasicResponse extends ClinicResponse {
    protected String name;
    protected String description;

    public ClinicBasicResponse(Integer id, String name, String description) {
        super(id);
        this.name = name;
        this.description = description;
    }

    public ClinicBasicResponse() {
    }
}
