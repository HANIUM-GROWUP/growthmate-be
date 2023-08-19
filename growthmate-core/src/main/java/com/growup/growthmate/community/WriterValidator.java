package com.growup.growthmate.community;

import com.growup.growthmate.BusinessException;
import org.springframework.stereotype.Component;

@Component
public class WriterValidator {

    private final CommunityException exception = CommunityException.unAuthorization();

    public void validate(CommunityBaseEntity entity, WriterId writerId) {
        if (!entity.isSameWriterId(writerId)) {
            throw new BusinessException(exception.getHttpStatusCode(), exception.getMessage());
        }
    }
}
