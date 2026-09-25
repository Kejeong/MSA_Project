package dev.project.boundedContext.post.domain;

import dev.project.shared.member.domain.ReplicaMember;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "POST_MEMBER")
@NoArgsConstructor
@Getter
public class PostMember extends ReplicaMember {
    public PostMember(String username, String password, String nickname, int activityScore) {
        super(username, password, nickname);
    }
}