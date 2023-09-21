package com.growup.growthmate;

import org.apache.poi.xssf.usermodel.XSSFRow;

public interface XSSFRowMapper<T> {

    T map(XSSFRow row);
}
