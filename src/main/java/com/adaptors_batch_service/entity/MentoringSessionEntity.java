package com.adaptors_batch_service.entity;

import com.adaptors_batch_service.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@NoArgsConstructor
@Entity(name = "source_mentoring_session")
@Table(
        indexes = {
        @Index(columnList = "is_deleted, start_date, start_time, end_date, end_time"
                , name= "idx_valid_session_time")
})
public class MentoringSessionEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "uuid", length = 50)
    @Comment("세션 UUID")
    private String uuid;

    @ManyToOne
    @JoinColumn(name = "mentoring_id") // FK 관계설정
    @Comment("멘토링 ID")
    private MentoringEntity mentoringEntity;

    @Column(name = "start_date")
    @Comment("시작 날짜")
    private LocalDate startDate;

    @Column(name = "end_date")
    @Comment("종료 날짜")
    private LocalDate endDate;

    @Column(name = "start_time")
    @Comment("시작 시간")
    private LocalTime startTime;

    @Column(name = "end_time")
    @Comment("종료 시간")
    private LocalTime endTime;

    @Column(name = "deadline_date")
    @Comment("예약 마감 날짜")
    private LocalDate deadlineDate;

    @Column(name = "min_head_count")
    @Comment("최소 인원")
    private Integer minHeadCount;

    @Column(name = "max_head_count")
    @Comment("최대 인원")
    private Integer maxHeadCount;

    @Column(name = "price")
    @Comment("볼트")
    private Integer price;

    @Column(name = "is_closed" , nullable = false)
    @Comment("마감 여부")
    private Boolean isClosed;

    @Column(name = "is_deleted" , nullable = false)
    @Comment("삭제 여부")
    private Boolean isDeleted;

    @Builder
    public MentoringSessionEntity(String uuid, MentoringEntity mentoringEntity,
                                  LocalDate startDate, LocalDate endDate,
                                  LocalTime startTime, LocalTime endTime, LocalDate deadlineDate,
                                  Integer minHeadCount, Integer maxHeadCount, Integer price,
                                  Boolean isClosed, Boolean isDeleted) {
        this.uuid = uuid;
        this.mentoringEntity = mentoringEntity;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.deadlineDate = deadlineDate;
        this.minHeadCount = minHeadCount;
        this.maxHeadCount = maxHeadCount;
        this.price = price;
        this.isClosed = isClosed;
        this.isDeleted = isDeleted;
    }
}
