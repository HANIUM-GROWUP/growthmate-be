package com.growup.growthmate;

import com.growup.growthmate.support.XSSFRowMapper;
import com.growup.growthmate.support.XSSSheetUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.batch.item.ItemReader;

import java.util.Optional;

public abstract class CompanyAbstractReader<T> implements ItemReader<T> {

    private static final int INITIAL_ROW_NUM = 1;

    private final XSSFSheet sheet;
    private final XSSFRowMapper<T> mapper;
    private int rowNum;

    protected CompanyAbstractReader(String path, XSSFRowMapper<T> mapper) {
        this.sheet = XSSSheetUtils.initialize(path);
        this.mapper = mapper;
        rowNum = INITIAL_ROW_NUM;
    }


    @Override
    public T read() {
        return Optional.ofNullable(sheet.getRow(rowNum++))
                .map(mapper::map)
                .orElse(null);
    }
}
