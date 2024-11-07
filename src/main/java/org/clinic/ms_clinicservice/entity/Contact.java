package org.clinic.ms_clinicservice.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "contact_info", schema = "ms_clinic_sc", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"id", "clinic_id", "work_phone", "work_email"})
})
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "clinic_id", nullable = false, unique = true)
    private Clinic clinic;

    @Column(name = "work_phone", length = 32)
    private String workPhone;

    @Column(name = "work_email", length = 252)
    private String workEmail;

}
