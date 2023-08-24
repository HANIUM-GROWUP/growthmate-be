package com.growup.growthmate.auth.domain;

import com.growup.growthmate.member.domain.Member;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface MemberOAuthRepository extends Repository<Member, Long> {

    Optional<Member> findByEmailAndRegistrationId(String email, String registrationId);
}
