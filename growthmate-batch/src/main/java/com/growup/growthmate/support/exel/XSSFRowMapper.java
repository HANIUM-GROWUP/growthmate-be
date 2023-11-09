package com.growup.growthmate.support.exel;

import org.apache.poi.xssf.usermodel.XSSFRow;

public interface XSSFRowMapper<T> {

    T map(XSSFRow row);
}
