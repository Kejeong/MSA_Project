package dev.project.boundedContext.post.app;

import dev.project.boundedContext.post.domain.Post;
import dev.project.boundedContext.post.domain.PostMember;
import dev.project.boundedContext.post.out.PostRepository;
import dev.project.global.eventPublisher.EventPublisher;
import dev.project.global.rsData.RsData;
import dev.project.shared.member.out.MemberApiClient;
import dev.project.shared.post.dto.PostDto;
import dev.project.shared.post.event.PostCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostWriteUseCase {
    private final PostRepository postRepository;
    private final EventPublisher eventPublisher;
    private final MemberApiClient memberApiClient;


    // 글 작성
    public RsData<Post> write(PostMember author, String title, String content) {
        Post post = postRepository.save(new Post(author, title, content));

        // 이벤트 발행
        eventPublisher.publish(
                new PostCreatedEvent(
                        new PostDto(post)
                )
        );

        String randomSecureTip = memberApiClient.getRandomSecureTip();

        return new RsData<>("201-1", "%d번 글이 생성되었습니다. 보안팁 : %s".formatted(post.getId(), randomSecureTip), post);
    }
}