package com.growup.growthmate.company.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "comapny_name_unique", columnNames = "name")
})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String imageUrl;

    @Column(nullable = false)
    private String ceo;

    @Column(nullable = false)
    private String scale;

    @Column(nullable = false)
    private String businessType;

    @Column(nullable = false)
    private String business;

    @Column(nullable = false)
    private LocalDateTime establishmentDate;

    @Column(nullable = false)
    private Long sales;

    @Column(nullable = false)
    private Long employeeNumber;

    @Column(nullable = false)
    private String address;

    public Company(String name,
                   String imageUrl,
                   String ceo,
                   String scale,
                   String businessType,
                   String business,
                   LocalDateTime establishmentDate,
                   Long sales,
                   Long employeeNumber,
                   String address) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.ceo = ceo;
        this.scale = scale;
        this.businessType = businessType;
        this.business = business;
        this.establishmentDate = establishmentDate;
        this.sales = sales;
        this.employeeNumber = employeeNumber;
        this.address = address;
    }

    public Company withId(Long id) {
        return new Company(
                id,
                this.name,
                this.imageUrl,
                this.ceo,
                this.scale,
                this.businessType,
                this.business,
                this.establishmentDate,
                this.sales,
                this.employeeNumber,
                this.address
        );
    }
}
