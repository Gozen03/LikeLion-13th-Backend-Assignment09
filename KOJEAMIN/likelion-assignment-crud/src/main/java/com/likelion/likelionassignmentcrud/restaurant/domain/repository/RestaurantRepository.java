package com.likelion.likelionassignmentcrud.restaurant.domain.repository;

import com.likelion.likelionassignmentcrud.restaurant.domain.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    // Pageable 객체를 받아 페이징 처리된 결과를 Page 객체로 반환
    @Override
    Page<Restaurant> findAll(Pageable pageable);
}