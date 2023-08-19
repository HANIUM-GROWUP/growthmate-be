package com.growup.growthmate.community.post.domain;

import com.growup.growthmate.community.WriterId;
import com.growup.growthmate.community.post.domain.value.CompanyId;
import com.growup.growthmate.community.post.domain.value.PostContent;
import com.growup.growthmate.community.post.domain.value.Title;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@SQLDelete(sql = "UPDATE post set is_deleted = 1 where post_id = ?")
@Where(clause = "is_deleted = 0")
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

    @Column(nullable = false)
    private boolean isDeleted;

    public Post(WriterId writerId, CompanyId companyId, Title title, PostContent content) {
        this.writerId = writerId;
        this.companyId = companyId;
        this.title = title;
        this.content = content;
        this.isDeleted = false;
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
