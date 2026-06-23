package com.likelion.likelionassignmentcrud.restaurant.api.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.List;

@Getter
@NoArgsConstructor
public class KakaoPlaceResponseDto {

    // 카카오가 보내주는 장소 목록 데이터 (배열 형태이므로 List로 받음)
    private List<PlaceInfo> documents;

    @Getter
    @NoArgsConstructor
    public static class PlaceInfo {
        private String place_name;     // 레스토랑 이름
        private String road_address_name; // 도로명 주소
        private String phone;             // 전화번호
        private String x;                 // 경도 (Longitude)
        private String y;                 // 위도 (Latitude)
    }
}