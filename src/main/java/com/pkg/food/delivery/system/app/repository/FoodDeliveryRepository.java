package com.pkg.food.delivery.system.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pkg.food.delivery.system.app.model.FoodEntity;

@Repository
public interface FoodDeliveryRepository extends JpaRepository<FoodEntity, Integer> {

	public List<FoodEntity> findAllByOrderByFoodPriceAsc();
}
