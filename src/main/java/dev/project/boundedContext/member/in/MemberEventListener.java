package dev.project.boundedContext.member.in;

import dev.project.boundedContext.member.domain.Member;
import dev.project.boundedContext.member.app.MemberFacade;
import dev.project.shared.post.event.PostCommentCreatedEvent;
import dev.project.shared.post.event.PostCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;
import static org.springframework.transaction.event.TransactionPhase.AFTER_COMMIT;

@Component
@RequiredArgsConstructor
public class MemberEventListener {
    private final MemberFacade memberFacade;

    // 글 작성 이벤트 발행시
    @TransactionalEventListener(phase = AFTER_COMMIT)
    @Transactional(propagation = REQUIRES_NEW)
    public void handle(PostCreatedEvent event) {
        Member member = memberFacade.findById(event.getPost().getAuthorId()).get();

        member.increaseActivityScore(3);
    }

    // 댓글 작성 이벤트 발행시
    @TransactionalEventListener(phase = AFTER_COMMIT)
    @Transactional(propagation = REQUIRES_NEW)
    public void handle(PostCommentCreatedEvent event) {
        Member member = memberFacade.findById(event.getPostComment().getAuthorId()).get();

        member.increaseActivityScore(1);
    }
}