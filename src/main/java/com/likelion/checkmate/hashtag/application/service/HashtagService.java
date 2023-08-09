package com.likelion.checkmate.hashtag.application.service;

import com.likelion.checkmate.hashtag.domain.repository.HashtagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HashtagService {

    private final HashtagRepository hashtagRepository;


}
