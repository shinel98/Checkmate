package com.likelion.checkmate.report.presentation.controller;

import com.likelion.checkmate.post.application.dto.PostDto;
import com.likelion.checkmate.post.presentation.request.PostRequest;
import com.likelion.checkmate.report.application.dto.ReportDto;
import com.likelion.checkmate.report.application.service.ReportService;
import com.likelion.checkmate.report.presentation.request.ReportRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReportController {

    private final ReportService reportService;
    @PostMapping("/report")
    public ResponseEntity<Long> create(@RequestBody ReportRequest request) {
        Long id = reportService.create(ReportDto.toDto(request));
        return ResponseEntity.ok(id);
    }
}
