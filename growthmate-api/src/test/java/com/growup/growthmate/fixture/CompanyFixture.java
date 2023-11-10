package com.growup.growthmate.fixture;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CompanyFixture {

    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // COMPANY DATA
    public static final String NAME = "비트 망고1";
    public static final String IMAGE_URL = "picure.com";
    public static final String BUSINESSTYPE = "모바일 게임, 소프트웨어 공급업";
    public static final LocalDateTime ESTABLISHMENTDATE = LocalDateTime.parse("1985-07-01 00:00:00", formatter);
    public static final String ADDRESS = "경기도 성남시 분당구 대왕판교로645번길 14 네오위즈판교타워 3층 (우)13487";
    public static final BigInteger EMPLOYMENTNUMBER = BigInteger.valueOf(43);
    public static final BigInteger SALES = BigInteger.valueOf(Long.parseLong("1394"));

    // COMPANY ANALYSIS DATA
    public static final Integer GROWTH = 80;
    public static final Integer STABILITY = 70;
    public static final Integer PROFITABILITY = 90;
    public static final Integer EFFICIENCY = 50;
    public static final Integer BUISNESS_PERFORMANCE = 60;

    // COMPANY COMPARISON DATA
    public static final Long SALES_FORECAST = 2881698135185L;
    public static final Double SALES_FORECAST_PERCENTAGE = 0.002673797;

}
