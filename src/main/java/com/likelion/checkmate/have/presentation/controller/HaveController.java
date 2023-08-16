package com.likelion.checkmate.have.presentation.controller;

import com.likelion.checkmate.have.application.dto.HaveDto;
import com.likelion.checkmate.have.application.service.HaveService;
import com.likelion.checkmate.have.presentation.request.HaveRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class HaveController {
    private final HaveService haveService;
    @PostMapping("/have")
    public ResponseEntity<Long> create(@RequestBody HaveRequest request) {

        Long id =  haveService.create(HaveDto.toDto(request));
        return ResponseEntity.ok(id);

    }

}
