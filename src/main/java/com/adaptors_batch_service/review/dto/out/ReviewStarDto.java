package com.adaptors_batch_service.review.dto.out;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewStarDto {
    private String mentoringUuid;
    private String mentorUuid;
    private Long reviewCount;
    private Double averageStar;
    private Long totalStar;

}