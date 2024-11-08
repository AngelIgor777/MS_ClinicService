package org.clinic.ms_clinicservice.response.ext;

import lombok.Data;
import org.clinic.ms_clinicservice.response.ClinicResponse;

@Data
public class ClinicIdResponse extends ClinicResponse {
    public ClinicIdResponse(Integer id) {
        super(id);
    }

    public ClinicIdResponse() {
    }
}
