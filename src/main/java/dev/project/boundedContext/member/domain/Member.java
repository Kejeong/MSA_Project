package dev.project.boundedContext.member.domain;

import dev.project.shared.member.domain.SourceMember;
import dev.project.shared.member.dto.MemberDto;
import dev.project.shared.member.event.MemberModifiedEvent;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "MEMBER_MEMBER")
@NoArgsConstructor
@Getter
public class Member extends SourceMember {
    public Member(String username, String password, String nickname) {
        super(username, password, nickname);
    }

    // 활동점수 증가
    public int increaseActivityScore(int amount) {
        if (amount == 0) return getActivityScore();

        setActivityScore(getActivityScore() + amount);

        // 회원 수정 이벤트 발행 -> 회원의 활동점수 변경 위함
        publishEvent(new MemberModifiedEvent(new MemberDto(this)));

        return getActivityScore();
    }
}
