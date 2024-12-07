package com.adaptors_batch_service.entity;

import com.adaptors_batch_service.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "source_mentoring")
@ToString
public class MentoringEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    @Comment("멘토링 Uuid")
    private String mentoringUuid;

    @Column(nullable = false, length = 50)
    @Comment("멘토링 이름")
    private String name;

    @Column(nullable = false, length = 150)
    @Comment("멘토링 설명")
    private String description;

    @Column(nullable = false, columnDefinition = "LONGTEXT") // 에디터 데이터(html)
    @Comment("멘토링 상세정보")
    private String detail;

    @Column(nullable = false, length = 50)
    @Comment("멘토 Uuid")
    private String mentorUuid;

    @Column(length = 200)
    @Comment("썸네일 url")
    private String thumbnailUrl;

    @Column(nullable = false)
    @Comment("내용 재사용 여부")
    private Boolean isReusable;

    @Column(nullable = false)
    @Comment("삭제 여부")
    private Boolean isDeleted;

}
