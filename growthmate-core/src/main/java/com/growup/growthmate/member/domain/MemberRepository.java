package com.growup.growthmate.member.domain;

import com.growup.growthmate.member.dto.MemberInfoResponse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Boolean existsByEmail(String email);
}
