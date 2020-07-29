package com.pkg.food.delivery.system.app.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class RestaurantEntity {

	@Id
	private int restaurantId;
	private int restaurantRating;
	@OneToMany(targetEntity = FoodEntity.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "rest_fk", referencedColumnName = "restaurantId")
	List<FoodEntity> foodEntity;

	public int getRestaurantRating() {
		return restaurantRating;
	}

	public void setRestaurantRating(int restaurantRating) {
		this.restaurantRating = restaurantRating;
	}

	public int getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}

	public List<FoodEntity> getFoodEntity() {
		return foodEntity;
	}

	public void setFoodEntity(List<FoodEntity> foodEntity) {
		this.foodEntity = foodEntity;
	}

}
