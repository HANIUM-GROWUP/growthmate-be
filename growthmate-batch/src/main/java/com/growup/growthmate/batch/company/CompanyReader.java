package com.growup.growthmate.batch.company;

import com.growup.growthmate.CompanyAbstractReader;
import com.growup.growthmate.company.domain.Company;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CompanyReader extends CompanyAbstractReader<Company> {

    public CompanyReader(@Value("${file-path.company}") String path, XSSFRowToCompanyMapper mapper) {
        super(path, mapper);
    }
}
