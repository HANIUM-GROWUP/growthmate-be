package com.growup.growthmate.support;

import org.apache.poi.xssf.usermodel.XSSFRow;

public interface XSSFRowMapper<T> {

    T map(XSSFRow row);
}
