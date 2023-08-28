package com.growup.growthmate.member.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(name = "unique_column_in_member", columnNames = "email")})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Length(min = 1 , max = 10, message = "이름은 최소 1자 부터 10자 이하여야 합니다.")
    private String name;

    private String email;

    private String pictureUrl;

    private String registrationId;

    @Builder
    public Member(String name, String email, String pictureUrl, String registrationId) {
        this.name = name;
        this.email = email;
        this.pictureUrl = pictureUrl;
        this.registrationId = registrationId;
    }

    public void updateName(String name) {
        this.name = name;
    }
}
