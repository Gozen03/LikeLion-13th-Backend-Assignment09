package com.likelion.likelionassignmentcrud.restaurant.application;

import com.likelion.likelionassignmentcrud.restaurant.api.dto.response.KakaoPlaceResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.StandardCharsets;

@Component
public class KakaoLocalClient {

    @Value("${kakao.api.key}")
    private String apiKey;

    private static final String KAKAO_BASE_URL = "https://dapi.kakao.com/v2/local/search/keyword.json";

    public KakaoPlaceResponseDto searchRestaurant(String keyword) {
        RestTemplate restTemplate = new RestTemplate();

        // 1. 헤더 설정 (카카오 API 키 인증)
        HttpHeaders headers = new HttpHeaders();

        if (!apiKey.startsWith("KakaoAK ")) {
            headers.set("Authorization", "KakaoAK " + apiKey);
        } else {
            headers.set("Authorization", apiKey);
        }

        HttpEntity<String> entity = new HttpEntity<>(headers);

        // 2. URL 및 파라미터 설정 (한글 깨짐 방지 인코딩 포함)
        URI targetUrl = UriComponentsBuilder.fromHttpUrl(KAKAO_BASE_URL)
                .queryParam("query", keyword)
                .queryParam("category_group_code", "FD6") // FD6 코드는 '음식점'만 필터링해 줍니다!
                .build()
                .encode(StandardCharsets.UTF_8)
                .toUri();

        // 3. API 호출 및 DTO로 반환 받기
        ResponseEntity<KakaoPlaceResponseDto> response = restTemplate.exchange(
                targetUrl,
                HttpMethod.GET,
                entity,
                KakaoPlaceResponseDto.class
        );

        return response.getBody();
    }
}