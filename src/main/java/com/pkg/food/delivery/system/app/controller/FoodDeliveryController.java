package com.pkg.food.delivery.system.app.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pkg.food.delivery.system.app.exception.FoodItemNotFoundException;
import com.pkg.food.delivery.system.app.model.FoodEntity;
import com.pkg.food.delivery.system.app.repository.FoodDeliveryRepository;

@RestController
@RequestMapping("/food")
public class FoodDeliveryController {

	@Autowired
	private FoodDeliveryRepository foodDeliveryRepository;

	// Method to get all food items
	@RequestMapping(path = "/getAllFoodItems", consumes = MediaType.APPLICATION_JSON_VALUE)
	public List<FoodEntity> getAllFoodItems() {
		return foodDeliveryRepository.findAll();
	}

	// method to get food item based on particular food id
	@RequestMapping(path = "/foods/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public FoodEntity getFoodItemsById(@PathVariable int id) throws Exception {
		FoodEntity foodItem = foodDeliveryRepository.findOne(id);
		if (foodItem == null)
			throw new FoodItemNotFoundException("Food Item Not Found With FoodId " + id);
		return foodItem;
	}

	// method to add new food item to menu
	@SuppressWarnings("rawtypes")
	@RequestMapping(method = RequestMethod.POST, value = "/addFoodItem", produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity addFoodItem(@RequestBody FoodEntity food) {
		foodDeliveryRepository.save(food);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(food.getFoodId())
				.toUri();
		return ResponseEntity.created(location).build();
	}

	// method to update some properties of specific foodItem
	@RequestMapping(method = RequestMethod.PUT, value = "/foods/{id}", produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE })
	public void updateFoodItem(@RequestBody FoodEntity food, @PathVariable int id) {
		FoodEntity foodEntity = foodDeliveryRepository.findOne(id);
		if (foodEntity == null)
			throw new FoodItemNotFoundException("Food Item Not Found With FoodId " + id);

		foodEntity.setFoodId(food.getFoodId());
		foodEntity.setFoodName(food.getFoodName());
		foodEntity.setFoodPrice(food.getFoodPrice());
		foodDeliveryRepository.save(foodEntity);
	}

	// method to remove a specific food item from menu
	@RequestMapping(method = RequestMethod.DELETE, value = "/foods/{id}")
	public void removeFoodItem(@PathVariable int id) {
		FoodEntity foodItem = foodDeliveryRepository.findOne(id);
		if (foodItem == null)
			throw new FoodItemNotFoundException("Food Item Not Found With FoodId " + id);
		foodDeliveryRepository.delete(id);
	}

	// Method to get all food items order by price asc
	@RequestMapping(path = "/foods/price", consumes = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE })
	public List<FoodEntity> getAllFoodItemsBasedOnPrice() {
		return foodDeliveryRepository.findAllByOrderByFoodPriceAsc();
	}
}
