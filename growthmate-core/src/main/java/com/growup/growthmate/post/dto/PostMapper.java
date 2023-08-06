package com.growup.growthmate.post.dto;

import com.growup.growthmate.post.domain.Post;
import com.growup.growthmate.post.domain.value.CompanyId;
import com.growup.growthmate.post.domain.value.PostContent;
import com.growup.growthmate.post.domain.value.Title;
import com.growup.growthmate.post.domain.value.WriterId;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PostMapper {

    public static Post toDomain(PostCreateCommand command) {
        return new Post(
                new WriterId(command.writerId()),
                new CompanyId(command.companyId()),
                new Title(command.title()),
                new PostContent(command.content())
        );
    }
}
