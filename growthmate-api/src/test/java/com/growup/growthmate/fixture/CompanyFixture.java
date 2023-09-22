package com.growup.growthmate.fixture;

import com.nimbusds.jose.shaded.gson.internal.bind.DefaultDateTypeAdapter;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class CompanyFixture {

    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static final Long COMPANY_ID = 1L;

    // COMPANY DATA
    public static final String NAME = "비트 망고";
    public static final String IMAGE_URL = "picure.com";
    public static final String BUSINESSTYPE = "모바일 게임, 소프트웨어 공급업";
    public static final LocalDateTime ESTABLISHMENTDATE = LocalDateTime.parse("1996-07-01 00:00:00", formatter);
    public static final String ADDRESS = "경기도 성남시 분당구 대왕판교로645번길 14 네오위즈판교타워 3층 (우)13487";
    public static final BigInteger EMPLOYMENTNUMBER = BigInteger.valueOf(43);
    public static final BigInteger SALES = BigInteger.valueOf(Long.parseLong("139485530000"));

    // COMPANY ANALYSIS DATA
    public static final Integer GROWTH = 80;
    public static final Integer STABILITY = 70;
    public static final Integer PROFITABILITY = 90;
    public static final Integer EFFICIENCY = 50;
    public static final Integer BUISNESS_PERFORMANCE = 60;

}
