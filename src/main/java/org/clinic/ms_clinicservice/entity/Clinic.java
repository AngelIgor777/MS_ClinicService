package org.clinic.ms_clinicservice.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "clinic", schema = "ms_clinic_sc")
@ToString(exclude = {"address", "contact"})
public class Clinic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "description")
    private String description;

    @Builder.Default
    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt = LocalDateTime.now();

    @OneToOne(mappedBy = "clinic", cascade = CascadeType.ALL,orphanRemoval = true)
    private Contact contact;

    @OneToOne(mappedBy = "clinic", cascade = CascadeType.ALL, orphanRemoval = true)
    private Address address;
}