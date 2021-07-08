package com.epam.flightscontroltool.domain.airplane.entity;

import com.epam.flightscontroltool.domain.staff.entity.StaffMember;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Airplane {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String number;

    private int capacity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @Enumerated(value = EnumType.STRING)
    private Manufacturer manufacturer;

    @OneToMany(mappedBy = "airplane", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<StaffMember> staffMembers = new ArrayList<>();
}