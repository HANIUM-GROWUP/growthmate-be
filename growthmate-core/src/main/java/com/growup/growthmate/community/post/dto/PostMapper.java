package com.growup.growthmate.community.post.dto;

import com.growup.growthmate.community.WriterId;
import com.growup.growthmate.community.post.domain.Post;
import com.growup.growthmate.community.post.domain.value.CompanyId;
import com.growup.growthmate.community.post.domain.value.PostContent;
import com.growup.growthmate.community.post.domain.value.Title;
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
