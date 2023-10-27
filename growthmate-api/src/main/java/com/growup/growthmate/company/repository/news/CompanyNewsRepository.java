package com.growup.growthmate.company.repository.news;

import com.growup.growthmate.company.repository.news.projection.CompanyNewsProjection;

import java.util.List;

public interface CompanyNewsRepository {

    List<CompanyNewsProjection> findCompanyNewsList(Long companyId);

}
