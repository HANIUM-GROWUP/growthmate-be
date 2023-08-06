package com.growup.growthmate.post.domain;

import com.growup.growthmate.post.domain.value.CompanyId;
import com.growup.growthmate.post.domain.value.PostContent;
import com.growup.growthmate.post.domain.value.Title;
import com.growup.growthmate.post.domain.value.WriterId;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @Embedded
    private WriterId writerId;

    @Embedded
    private CompanyId companyId;

    @Embedded
    private Title title;

    @Embedded
    private PostContent content;

    public Post(WriterId writerId, CompanyId companyId, Title title, PostContent content) {
        this.writerId = writerId;
        this.companyId = companyId;
        this.title = title;
        this.content = content;
    }

    public void updateTitle(Title title) {
        this.title = title;
    }

    public void updateContent(PostContent content) {
        this.content = content;
    }

    public boolean isSameWriterId(WriterId writerId) {
        return this.writerId.equals(writerId);
    }
}
