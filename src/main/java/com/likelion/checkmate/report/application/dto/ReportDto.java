package com.likelion.checkmate.report.application.dto;

import com.likelion.checkmate.post.application.dto.PostDto;
import com.likelion.checkmate.post.presentation.request.PostRequest;
import com.likelion.checkmate.report.presentation.request.ReportRequest;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReportDto {
    private Long postId;
    private String content;
    public static ReportDto toDto(ReportRequest request) {
        return ReportDto.builder()
                .postId(request.getPostId())
                .content(request.getContent())
                .build();
    }
}
