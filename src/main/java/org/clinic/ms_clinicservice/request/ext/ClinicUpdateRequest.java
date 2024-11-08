package org.clinic.ms_clinicservice.request.ext;

import org.clinic.ms_clinicservice.request.ClinicRequest;

public class ClinicUpdateRequest extends ClinicRequest {
    public ClinicUpdateRequest(String name, String description) {
        super(name, description);
    }

    public ClinicUpdateRequest() {
    }
}
