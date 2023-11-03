package com.growup.growthmate.support;

import com.growup.growthmate.BusinessException;

public class ExelNotFoundException extends BusinessException {
    public ExelNotFoundException(String path) {
        super(400, path + " 엑셀 파일을 찾을 수 없습니다.");
    }
}
