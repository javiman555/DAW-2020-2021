package com.trec.services;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.annotation.PostConstruct;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.trec.models.Dish;
import com.trec.repositories.DishRepository;


@Service
public class DatabaseInitializer {

	@Autowired
	private DishRepository dishRepository;


	@PostConstruct
	public void init() throws IOException, URISyntaxException {

		// Sample books

		Dish dish1 = new Dish("Cereales con leche", 5.00f, "Desayuno","Cena");
		dishRepository.save(dish1);
	}
}
/*		setBookImage(dish1, "/sample_images/tus_zonas_erroneas.jpg");
		dishRepository.save(dish1);

		Dish dish2 = new Dish("Tortilla de patatas", 5.00f, "Comida", "Cena");

		setBookImage(dish2, "/sample_images/la_vida_secreta_de_la_mente.jpg");
		dishRepository.save(dish2);


	}

	public void setBookImage(Dish dish, String classpathResource) throws IOException {
		dish.setImage(true);
		Resource image = new ClassPathResource(classpathResource);
		dish.setImageFile(BlobProxy.generateProxy(image.getInputStream(), image.contentLength()));
	}
}
*/