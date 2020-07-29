package com.pkg.food.delivery.system.app;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pkg.food.delivery.system.app.model.RestaurantEntity;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

	@Autowired
	RestaurantRepository restaurantRepository;

	// Method to get all Restaurants
	@RequestMapping(path = "/getAllRestaurants", consumes = MediaType.APPLICATION_JSON_VALUE)
	public List<RestaurantEntity> getAll() {
		return restaurantRepository.findAll();
	}

	// method to add new restaurant
	@SuppressWarnings("rawtypes")
	@RequestMapping(method = RequestMethod.POST, value = "/addResturant", produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity addResturant(@RequestBody RestaurantEntity restaurant) {
		restaurantRepository.save(restaurant);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(restaurant.getRestaurantId()).toUri();
		return ResponseEntity.created(location).build();
	}

	// Method to get all restaurants based on ratings.
	@RequestMapping(path = "/restaurants/rating", consumes = MediaType.APPLICATION_JSON_VALUE)
	public List<RestaurantEntity> getAllRestaurantsByRating() {
		return restaurantRepository.findAllByOrderByRestaurantRatingAsc();
	}
}
