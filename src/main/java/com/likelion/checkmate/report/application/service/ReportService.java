package com.likelion.checkmate.report.application.service;

import com.likelion.checkmate.post.application.dto.PostHomeDto;
import com.likelion.checkmate.post.domain.entity.Post;
import com.likelion.checkmate.post.domain.repository.PostRepository;
import com.likelion.checkmate.report.application.dto.ReportDto;
import com.likelion.checkmate.report.domain.entity.Report;
import com.likelion.checkmate.report.domain.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportService {
    private final ReportRepository reportRepository;

    private final PostRepository postRepository;

    @Transactional
    public Long create(ReportDto dto)  {
        Post post = postRepository.findById(dto.getPostId()).orElseThrow(() -> new IllegalArgumentException("no such post"));;
        Report report = Report.toEntity(dto.getContent(), post);
        Report savedReport = reportRepository.save(report);
        return savedReport.getId();
    }
}
