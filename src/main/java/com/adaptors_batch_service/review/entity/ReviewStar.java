package com.adaptors_batch_service.review.entity;

import com.adaptors_batch_service.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicUpdate;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@DynamicUpdate
@Table(name = "review_star", uniqueConstraints = {
        @UniqueConstraint(columnNames = "mentoring_uuid") // productId에 대한 유니크 제약 조건 설정
})
public class ReviewStar extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Comment("멘토링 uuid")
    @Column(nullable = false, unique = true) // productId에 대해 UNIQUE 제약 조건 설정
    private String mentoringUuid;

    @Comment("멘토 uuid")
    @Column(nullable = false)
    private String mentorUuid;

    @Comment("총 리뷰 개수")
    @Column(nullable = false)
    private Long reviewCount;

    @Comment("평균 별점")
    @Column(nullable = false)
    private Double averageStar;

    @Comment("총 별점의 합")
    @Column(nullable = false)
    private Long totalStar; // 평균을 계산하기 위해 총 별점 합계를 저장




}
