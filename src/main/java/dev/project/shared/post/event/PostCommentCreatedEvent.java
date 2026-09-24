package dev.project.shared.post.event;

import dev.project.shared.post.dto.PostCommentDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostCommentCreatedEvent {
    private final PostCommentDto postComment;
}
