package dev.project.boundedContext.post.domain;

import dev.project.global.jpa.entity.BaseIdAndTime;
import dev.project.shared.post.dto.PostCommentDto;
import dev.project.shared.post.event.PostCommentCreatedEvent;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.PERSIST;
import static jakarta.persistence.CascadeType.REMOVE;

@Entity
@Table(name = "POST_POST")
@NoArgsConstructor
@Getter
public class Post extends BaseIdAndTime {
    @ManyToOne(fetch = FetchType.LAZY)
    private PostMember author;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String content;
    @OneToMany(mappedBy = "post", cascade = {PERSIST, REMOVE}, orphanRemoval = true)
    private List<PostComment> comments = new ArrayList<>();

    public Post(PostMember author, String title, String content) {
        this.author = author;
        this.title = title;
        this.content = content;
    }

    // 댓글 추가
    public PostComment addComment(PostMember author, String content) {
        PostComment postComment = new PostComment(this, author, content);

        comments.add(postComment);

        // 이벤트 발행
        publishEvent(
                new PostCommentCreatedEvent(new PostCommentDto(postComment))
        );

        return postComment;
    }

    // 댓글 존재여부
    public boolean hasComments() {
        return !comments.isEmpty();
    }
}
