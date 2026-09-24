package dev.project.boundedContext.member.domain;


import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class MemberPolicy {
    private static final int PASSWORD_CHANGE_DAYS = 90;

    // 화면이나 메시지에 숫자로 넣을 때
    public Duration getNeedToChangePasswordPeriod() {
        return Duration.ofDays(PASSWORD_CHANGE_DAYS);
    }

    // 지금 비밀번호를 변경해야 하는지 판단할 때
    public int getNeedToChangePasswordDays() {
        return PASSWORD_CHANGE_DAYS;
    }

    // 만료일 계산처럼 날짜 연산에 사용할 때
    public boolean isNeedToChangePassword(LocalDateTime lastChangeDate) {
        if (lastChangeDate == null) return true;

        return lastChangeDate.plusDays(PASSWORD_CHANGE_DAYS)
                .isBefore(LocalDateTime.now());
    }
}