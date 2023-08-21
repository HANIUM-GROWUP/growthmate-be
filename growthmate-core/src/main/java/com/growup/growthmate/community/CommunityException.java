package com.growup.growthmate.community;

public interface CommunityException {

    int getHttpStatusCode();

    String getMessage();

    static CommunityException unAuthorization() {
        return new CommunityException() {
            @Override
            public int getHttpStatusCode() {
                return 403;
            }

            @Override
            public String getMessage() {
                return "해당 작성자가 접근할 수 없습니다.";
            }
        };
    }
}
