package com.likelion.likelionassignmentcrud.restaurant.domain;

import com.likelion.likelionassignmentcrud.menu.domain.Menu;
import com.likelion.likelionassignmentcrud.restaurant.api.dto.request.RestaurantUpdateRequestDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "restaurant_id")
    private Long restaurantId;

    private String name;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Category category;

    @OneToMany(mappedBy = "restaurant",
            cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Menu> menus = new ArrayList<>();

    @Builder
    private Restaurant(String name, Category category) {
        this.name = name;
        this.category = category;
    }

    public void update(RestaurantUpdateRequestDto restaurantUpdateRequestDto) {
        this.name = restaurantUpdateRequestDto.name();
        this.category = restaurantUpdateRequestDto.category();
    }
}