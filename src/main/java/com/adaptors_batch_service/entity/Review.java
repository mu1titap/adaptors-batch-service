package com.adaptors_batch_service.entity;

import com.adaptors_batch_service.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;

@Entity(name = "source_review")
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("리뷰 코드")
    @Column(nullable = false)
    private String reviewCode;

    @Comment("리뷰 제목")
    @Column(nullable = false)
    private String title;

    @Comment("리뷰 내용")
    @Column(nullable = false)
    private String comment;

    @Comment("별점")
    @Column(nullable = false)
    private int score;

    @Comment("멘토링 uuid")
    @Column(nullable = false)
    private String mentoringUuid;

    @Comment("멘토링 세션 uuid")
    @Column(nullable = false)
    private String mentoringSessionUuid;

    @Comment("멘티 uuid")
    @Column(nullable = false)
    private String menteeUuid;

    @Comment("멘토 uuid")
    @Column(nullable = false)
    private String mentorUuid;

    @Comment("삭제 여부")
    @Column(nullable = false)
    private boolean isDeleted;


}
