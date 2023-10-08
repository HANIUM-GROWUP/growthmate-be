package com.growup.growthmate.company;

import com.growup.growthmate.company.application.CompanyService;
import com.growup.growthmate.company.dto.analysis.CompanyAnalysisRequest;
import com.growup.growthmate.company.dto.analysis.CompanyAnalysisResponse;
import com.growup.growthmate.company.dto.find.CompanyDetailRequest;
import com.growup.growthmate.company.dto.find.CompanyDetailResponse;
import com.growup.growthmate.company.dto.find.SortedCompanyRequest;
import com.growup.growthmate.company.dto.find.SortedCompanyResponse;
import com.growup.growthmate.isolation.TestIsolation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static com.growup.growthmate.fixture.CommunityFixture.COMPANY_ID;
import static com.growup.growthmate.fixture.CompanyFixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest
@TestIsolation
@Sql(scripts = "/company_fixture.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class CompanyServiceTest {

    private static final Integer DEFAULT_SIZE = 10;

    @Autowired
    private CompanyService companyService;

    @Nested
    @DisplayName("기업 전체 조회")
    class CompanySelectTest {

        @Test
        void 기본_조회해서_최근_10개를_가져온다() {

            SortedCompanyRequest request = new SortedCompanyRequest(null, DEFAULT_SIZE, "id");

            // when
            List<SortedCompanyResponse> actual = companyService.findAllCompanies(request);

            // then
            assertThat(actual)
                    .map(SortedCompanyResponse::name)
                    .containsExactly("비트 망고15", "비트 망고14", "비트 망고13", "비트 망고12", "비트 망고11",
                            "비트 망고10", "비트 망고9", "비트 망고8", "비트 망고7", "비트 망고6");

        }

        @Test
        void cursor를_지정해서_이전_정보를_가져온다() {

            // given
            SortedCompanyRequest request = new SortedCompanyRequest(13L, DEFAULT_SIZE, "id");

            // when
            List<SortedCompanyResponse> actual = companyService.findAllCompanies(request);

            // then
            assertThat(actual)
                    .map(SortedCompanyResponse::name)
                    .containsExactly("비트 망고12", "비트 망고11", "비트 망고10", "비트 망고9", "비트 망고8",
                            "비트 망고7", "비트 망고6", "비트 망고5", "비트 망고4", "비트 망고3");

        }

        @Test
        void sort를_establishmentDate로_지정해서_정렬을_한다() {

            // given
            SortedCompanyRequest request = new SortedCompanyRequest(13L, DEFAULT_SIZE, "establishmentDate");

            // when
            List<SortedCompanyResponse> actual = companyService.findAllCompanies(request);

            // then
            assertThat(actual)
                    .map(SortedCompanyResponse::name)
                    .containsExactly("비트 망고12", "비트 망고11", "비트 망고10", "비트 망고9", "비트 망고8",
                            "비트 망고7", "비트 망고6", "비트 망고5", "비트 망고3", "비트 망고4");

        }

        @Test
        void sort를_sales로_지정해서_정렬을_한다() {

            // given
            SortedCompanyRequest request = new SortedCompanyRequest(13L, DEFAULT_SIZE, "sales");

            // when
            List<SortedCompanyResponse> actual = companyService.findAllCompanies(request);

            // then
            assertThat(actual)
                    .map(SortedCompanyResponse::name)
                    .containsExactly("비트 망고3", "비트 망고4", "비트 망고5", "비트 망고6", "비트 망고7",
                            "비트 망고8", "비트 망고9", "비트 망고10", "비트 망고11", "비트 망고12");

        }

    }

    @Nested
    @DisplayName("기업 상세 정보 조회")
    class CompanyDetailInfoTest {

        @Test
        void 기업_정보_상세_조회한다() {

            //given
            CompanyDetailRequest request = new CompanyDetailRequest(COMPANY_ID);

            //when
            CompanyDetailResponse response = companyService.findCompanyDetail(request);

            //then
            assertAll(
                    () -> assertThat(response.name()).isEqualTo(NAME),
                    () -> assertThat(response.imageUrl()).isEqualTo(IMAGE_URL),
                    () -> assertThat(response.businessType()).isEqualTo(BUSINESSTYPE),
                    () -> assertThat(response.establishmentDate()).isEqualTo(ESTABLISHMENTDATE),
                    () -> assertThat(response.address()).isEqualTo(ADDRESS),
                    () -> assertThat(response.employeeNumber()).isEqualTo(EMPLOYMENTNUMBER),
                    () -> assertThat(response.sales()).isEqualTo(SALES)
            );
        }

    }

    @Nested
    @DisplayName("오각형 변수 조회")
    class CompanyAnalysisTest {

        @Test
        void 오각형_변수가_companyid_에_맞게_조회된다() {

            //given
            CompanyAnalysisRequest request = new CompanyAnalysisRequest(COMPANY_ID);

            //when
            CompanyAnalysisResponse response = companyService.findCompanyAnalysis(request);

            //then
            assertAll(
                    () -> assertThat(response.growth()).isEqualTo(GROWTH),
                    () -> assertThat(response.stability()).isEqualTo(STABILITY),
                    () -> assertThat(response.profitability()).isEqualTo(PROFITABILITY),
                    () -> assertThat(response.efficiency()).isEqualTo(EFFICIENCY),
                    () -> assertThat(response.businessPerformance()).isEqualTo(BUISNESS_PERFORMANCE)
            );
        }

    }

}
