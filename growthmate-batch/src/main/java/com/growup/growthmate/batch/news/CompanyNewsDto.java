package com.growup.growthmate.batch.news;

public record CompanyNewsDto(
        String name,
        String title,
        String description,
        String url,
        String sentiment
) {
}
