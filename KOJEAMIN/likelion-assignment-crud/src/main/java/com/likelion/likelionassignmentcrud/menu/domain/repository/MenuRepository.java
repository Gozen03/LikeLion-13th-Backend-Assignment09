package com.likelion.likelionassignmentcrud.menu.domain.repository;

import com.likelion.likelionassignmentcrud.menu.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long> {

    // 특정 음식점 ID에 속한 모든 메뉴를 리스트로 반환하는 메서드
    List<Menu> findByRestaurant_RestaurantId(Long restaurantId);

}