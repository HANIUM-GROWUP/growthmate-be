package com.growup.growthmate.member.domain;

import com.growup.growthmate.BusinessException;
import com.growup.growthmate.community.post.exception.PostException;
import com.growup.growthmate.member.exception.MemberException;
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

    private String name;

    private String email;

    private String pictureUrl;

    private String registrationId;

    public Member(String name, String email, String pictureUrl, String registrationId) {
        this.name = name;
        this.email = email;
        this.pictureUrl = pictureUrl;
        this.registrationId = registrationId;
    }

    public void updateName(String name) {
        validateNameLength(name);
        this.name = name;
    }

    private void validateNameLength(String value) {
        if (value == null || value.isBlank() || value.length() > 11) {
            MemberException exception = MemberException.INVALID_NAME;
            throw new BusinessException(exception.getHttpStatusCode(), exception.getMessage());
        }
    }
}
