package dev.project.shared.member.event;

import dev.project.shared.member.dto.MemberDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberJoinedEvent {
    private final MemberDto member;
}
