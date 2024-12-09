package com.adaptors_batch_service.mentoringOverview.entity;

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
@Table(name = "mentoring_overview", uniqueConstraints = {
        @UniqueConstraint(columnNames = "mentoring_uuid") // 에 대한 유니크 제약 조건 설정
}, indexes = {
        @Index(name = "idx_total_score", columnList = "total_score")
})
public class MentoringOverView  extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "mentoring_uuid", nullable = false, unique = true)
    private String mentoringUuid;

    @Column(name = "review_score", nullable = false)
    private Double reviewScore;

    @Column(name = "total_review_count", nullable = false)
    private Long totalReviewCount;

    @Column(name = "review_star_avg", nullable = false)
    private Double reviewStarAvg;

    @Column(name = "sale_score", nullable = false)
    private Double saleScore;

    @Column(name = "total_sale_count", nullable = false)
    private Long totalSaleCount;

    @Column(name = "total_score", nullable = false)
    private Double totalScore;
}
