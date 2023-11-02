package com.growup.growthmate.batch.news;

import com.growup.growthmate.CompanyAbstractReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CompanyNewsReader extends CompanyAbstractReader<CompanyNewsDto> {

    public CompanyNewsReader(@Value("${file-path.company-news}") String path,
                             XSSRowToCompanyNewsMapper mapper) {
        super(path, mapper);
    }
}
