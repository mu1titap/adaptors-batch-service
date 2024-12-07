package com.adaptors_batch_service.mentorOverview.entity;

import com.adaptors_batch_service.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@DynamicUpdate
@Table(name = "mentor_overview", uniqueConstraints = {
        @UniqueConstraint(columnNames = "mentor_uuid") // 에 대한 유니크 제약 조건 설정
})
public class MentorOverView extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = true)
    private String nickName;

    @Column(nullable = true)
    private String profileImageUrl;

    @Column(name = "mentor_uuid", nullable = false, unique = true)
    private String mentorUuid;

    @Column(name = "review_score", nullable = false)
    private Double reviewScore;

    @Column(name = "total_review_count", nullable = false)
    private Long totalReviewCount;

    @Column(name = "review_star_avg", nullable = false)
    private Double reviewStarAvg;

    @Column(name = "like_score", nullable = false)
    private Double likeScore;

    @Column(name = "total_like_count", nullable = false)
    private Long totalLikeCount;

    @Column(name = "sale_score", nullable = false)
    private Double saleScore;

    @Column(name = "total_sale_count", nullable = false)
    private Long totalSaleCount;

    @Column(name = "total_score", nullable = false)
    private Double totalScore;


}
