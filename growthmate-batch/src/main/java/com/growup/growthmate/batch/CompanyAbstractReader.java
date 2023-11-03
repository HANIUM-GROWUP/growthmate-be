package com.growup.growthmate.batch;

import com.growup.growthmate.support.exel.XSSFRowMapper;
import com.growup.growthmate.support.exel.XSSSheetUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.batch.item.ItemReader;

import java.util.Optional;

public abstract class CompanyAbstractReader<T> implements ItemReader<T> {

    private static final int INITIAL_ROW_NUM = 1;

    private final XSSFRowMapper<T> mapper;
    private final String path;
    private XSSFSheet sheet;
    private int rowNum;

    protected CompanyAbstractReader(String path, XSSFRowMapper<T> mapper) {
        this.mapper = mapper;
        this.path = path;
        rowNum = INITIAL_ROW_NUM;
    }


    @Override
    public T read() {
        if (sheet == null) {
            sheet = XSSSheetUtils.initialize(path);
        }
        return Optional.ofNullable(sheet.getRow(rowNum++))
                .map(mapper::map)
                .orElse(null);
    }
}
