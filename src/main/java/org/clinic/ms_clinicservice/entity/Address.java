package org.clinic.ms_clinicservice.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "address", schema = "ms_clinic_sc", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"id", "clinic_id", "city", "street", "building_number"})
})
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "clinic_id", nullable = false, unique = true)
    private Clinic clinic;

    @Column(name = "city", nullable = false, length = 128)
    private String city;

    @Column(name = "street", nullable = false, length = 252)
    private String street;

    @Column(name = "building_number", nullable = false, length = 32)
    private String buildingNumber;

}
