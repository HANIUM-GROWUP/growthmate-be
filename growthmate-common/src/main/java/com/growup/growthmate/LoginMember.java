package com.growup.growthmate;

public record LoginMember(Long id) {

    public static final LoginMember NOT_LOGIN = new LoginMember(0L);
}
