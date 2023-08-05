package com.likelion.checkmate.report.domain.repository;

import com.likelion.checkmate.report.domain.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Long> {
}
