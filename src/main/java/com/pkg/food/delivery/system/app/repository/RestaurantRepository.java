package com.pkg.food.delivery.system.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pkg.food.delivery.system.app.model.RestaurantEntity;

@Repository
public interface RestaurantRepository extends JpaRepository<RestaurantEntity, Integer> {
	public List<RestaurantEntity> findAllByOrderByRestaurantRatingAsc();
}
