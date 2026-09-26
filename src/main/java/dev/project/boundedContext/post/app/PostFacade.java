package dev.project.boundedContext.post.app;

import dev.project.boundedContext.post.domain.Post;
import dev.project.boundedContext.post.domain.PostMember;
import dev.project.boundedContext.post.out.PostMemberRepository;
import dev.project.boundedContext.post.out.PostRepository;
import dev.project.global.rsData.RsData;
import dev.project.shared.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostFacade {
    private final PostRepository postRepository;
    private final PostMemberRepository postMemberRepository;
    private final PostWriteUseCase postWriteUseCase;

    @Transactional(readOnly = true)
    public long count() {
        return postRepository.count();
    }

    // 글 작성
    @Transactional
    public RsData<Post> write(PostMember author, String title, String content) {
        return postWriteUseCase.write(author, title, content);
    }

    // 글 ID 조회
    @Transactional(readOnly = true)
    public Optional<Post> findById(int id) {
        return postRepository.findById(id);
    }

    // PostMember 와 Member 동기화
    @Transactional
    public PostMember syncMember(MemberDto member) {
        PostMember _member = new PostMember(
                member.getId(),
                member.getCreateDate(),
                member.getModifyDate(),
                member.getUsername(),
                "",
                member.getNickname(),
                member.getActivityScore()
        );

        return postMemberRepository.save(_member);
    }

    // 회원명 조회
    @Transactional(readOnly = true)
    public Optional<PostMember> findMemberByUsername(String username) {
        return postMemberRepository.findByUsername(username);
    }
}