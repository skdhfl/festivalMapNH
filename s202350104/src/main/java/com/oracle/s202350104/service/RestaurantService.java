package com.oracle.s202350104.service;

import java.util.List;

import com.oracle.s202350104.model.Restaurants;
import com.oracle.s202350104.model.RestaurantsContent;

public interface RestaurantService {

	List<RestaurantsContent> 	listRestaurant(RestaurantsContent restaurant);
	RestaurantsContent  		detailRestaurant(int contentId);
	int 						totalRestaurant();
	int                 		conTotalRestaurant(RestaurantsContent restaurant);
	List<RestaurantsContent> 	listSearchRestaurant(RestaurantsContent restaurant);
	List<RestaurantsContent>    listRestaurant();
	

}
