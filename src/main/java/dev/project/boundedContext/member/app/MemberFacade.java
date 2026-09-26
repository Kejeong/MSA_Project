package dev.project.boundedContext.member.app;

import dev.project.boundedContext.member.domain.Member;
import dev.project.boundedContext.member.domain.MemberPolicy;
import dev.project.boundedContext.member.out.MemberRepository;
import dev.project.global.rsData.RsData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberFacade {
    private final MemberRepository memberRepository;
    private final MemberJoinUseCase memberJoinUseCase;
    private final MemberPolicy memberPolicy;

    @Transactional(readOnly = true)
    public long count() {
        return memberRepository.count();
    }

    // 회원가입
    @Transactional
    public RsData<Member> join(String username, String password, String nickname) {
        return memberJoinUseCase.join(username, password, nickname);
    }

    // 회원명 조회
    @Transactional(readOnly = true)
    public Optional<Member> findByUsername(String username) {
        return memberRepository.findByUsername(username);
    }

    // 회원ID 조회
    @Transactional(readOnly = true)
    public Optional<Member> findById(int id) {
        return memberRepository.findById(id);
    }

    // 비밀번호 랜덤팁 생성
    public String getRandomSecureTip() {
        return "비밀번호의 유효기간은 %d일 입니다."
                .formatted(memberPolicy.getNeedToChangePasswordDays());
    }
}