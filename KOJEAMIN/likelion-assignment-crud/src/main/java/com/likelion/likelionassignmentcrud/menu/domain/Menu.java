package com.likelion.likelionassignmentcrud.menu.domain;

import com.likelion.likelionassignmentcrud.menu.api.dto.request.MenuUpdateRequestDto;
import com.likelion.likelionassignmentcrud.restaurant.domain.Restaurant;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_id")
    private Long menuId;

    private String name;

    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id") // 외래 키 설정
    private Restaurant restaurant;

    @Builder
    private Menu(String name, int price, Restaurant restaurant) {
        this.name = name;
        this.price = price;
        this.restaurant = restaurant;
    }

    public void update(MenuUpdateRequestDto menuUpdateRequestDto) {
        this.name = menuUpdateRequestDto.name();
        this.price = menuUpdateRequestDto.price();
    }
}