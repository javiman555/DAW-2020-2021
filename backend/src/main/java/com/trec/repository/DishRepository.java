package com.trec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.trec.model.Dish;

public interface DishRepository extends JpaRepository<Dish, Long> {

	 @Query("SELECT d FROM Dish d WHERE d.category = :c")
	 public List<Dish> getByCategory(String c);
	 
	 //Top 3 of dishes that the user tolerates and are more popular
	 @Query(value ="SELECT * "
	 		+ "FROM trec.dish AS d "
	 		+ "WHERE d.id not in "
	 		//List of dish ids that we know the user tolerates
	 		+ "(SELECT  DISTINCT di.dish_id "
	 		+ "FROM trec.dish_ing AS di "
	 		+ "WHERE di.ing_id in "
	 		//List of ingredients that we know the user tolerates
	 		+ "(SELECT  DISTINCT trec.ingredient.id "
	 		+ "FROM trec.ingredient "
	 		+ "WHERE trec.ingredient.name_allergen IS NOT NULL AND trec.ingredient.name_allergen NOT in  "
	 		//List of allergens that the user tolerates
	 		+ "(SELECT DISTINCT name_allergen "
	 		+ "FROM (SELECT * FROM trec.user WHERE id = :iduser) AS u "
	 		+ "JOIN trec.purchase AS p ON u.id = p.user_id "
	 		+ "JOIN trec.pur_dish AS pd ON p.id = pd.pur_id "
	 		+ "JOIN trec.dish_ing AS di  ON pd.dish_id = di.dish_id "
	 		+ "JOIN trec.ingredient AS i ON i.id = di.ing_id))) "
	 		+ "ORDER BY nbuy DESC "
	 		+ "LIMIT 3 " 
	 		
			 ,nativeQuery = true
			 )
	 public List<Dish> getRecomended(long iduser);
}