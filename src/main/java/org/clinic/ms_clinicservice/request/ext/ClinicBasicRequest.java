package org.clinic.ms_clinicservice.request.ext;

import lombok.Data;
import org.clinic.ms_clinicservice.request.ClinicRequest;

@Data
public class ClinicBasicRequest extends ClinicRequest {
    private int id;

    public ClinicBasicRequest(int id, String name, String description) {
        super(name, description);
        this.id = id;
    }

    public ClinicBasicRequest(String name, String description) {
        super(name, description);
    }

    public ClinicBasicRequest() {
    }
}
