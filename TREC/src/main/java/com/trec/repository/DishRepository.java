package com.trec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.trec.model.Dish;

public interface DishRepository extends JpaRepository<Dish, Long> {

	 @Query("SELECT d FROM Dish d WHERE d.category = :c")
	 public List<Dish> getByCategory(String c);
	 
	 @Query(value = 
			 "SELECT * FROM Dish AS d WHERE d.id in (SELECT  DISTINCT di.DISH_ID "
	 		+ "FROM User AS u "
	 		+ "JOIN Purchase AS p ON u.id = p.User_ID "
	 		+ "JOIN PUR_DISH AS pd ON p.id = pd.PUR_ID "
	 		+ "JOIN DISH_ING AS di  ON pd.DISH_ID = di.DISH_ID "
	 		+ "JOIN Ingredient AS i ON i.id = di.ING_ID "
	 		+ "WHERE i.name_allergen IS NULL OR i.name_allergen in "
	 		+ "(SELECT DISTINCT name_allergen "
	 		+ "FROM (SELECT * FROM User WHERE id = :iduser) AS u "
	 		+ "JOIN Purchase AS p ON u.id = p.User_ID "
	 		+ "JOIN PUR_DISH AS pd ON p.id = pd.PUR_ID "
	 		+ "JOIN DISH_ING AS di  ON pd.DISH_ID = di.DISH_ID "
	 		+ "JOIN Ingredient AS i ON i.id = di.ING_ID)) "
	 		+ "ORDER BY nbuy DESC "
	 		+ "LIMIT 3 "
			 ,nativeQuery = true
			 )
	 public List<Dish> getRecomended(long iduser);
}