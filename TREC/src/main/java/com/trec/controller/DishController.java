package com.trec.controller;

import java.io.IOException;
import java.security.Principal;
import java.sql.SQLException;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.trec.model.Dish;
import com.trec.service.DishService;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class DishController {

	@Autowired
	private DishService dishService;

	@ModelAttribute
	public void addAttributes(Model model, HttpServletRequest request) {

		Principal principal = request.getUserPrincipal();

		if (principal != null) {

			model.addAttribute("logged", true);
			model.addAttribute("userName", principal.getName());
			model.addAttribute("admin", request.isUserInRole("ADMIN"));

		} else {
			model.addAttribute("logged", false);
		}
	}
	@GetMapping("/carta")
	public String showDishes(Model model) {

		model.addAttribute("dishes", dishService.findAll());

		return "carta";
	}
	
	@GetMapping("/dishes/{id}")
	public String showDish(Model model, @PathVariable long id) {

		Optional<Dish> dish = dishService.findById(id);
		if (dish.isPresent()) {
			model.addAttribute("dish", dish.get());
			return "dish";
		} else {
			return "/carta";
		}

	}

	@GetMapping("/dishes/{id}/image")
	public ResponseEntity<Object> downloadImage(@PathVariable long id) throws SQLException {

		Optional<Dish> dish = dishService.findById(id);
		if (dish.isPresent() && dish.get().getImageFile() != null) {

			Resource file = new InputStreamResource(dish.get().getImageFile().getBinaryStream());

			return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
					.contentLength(dish.get().getImageFile().length()).body(file);

		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/removedish/{id}")
	public String removeDish(Model model, @PathVariable long id) {

		Optional<Dish> dish = dishService.findById(id);
		if (dish.isPresent()) {
			dishService.delete(id);
			model.addAttribute("book", dish.get());
		}
		return "removeddish";
	}

	@GetMapping("/newdish")
	public String newDish(Model model) {

		return "newdish";
	}

	@PostMapping("/newdish")
	public String newDishProcess(Model model, Dish dish, MultipartFile imageField) throws IOException {

		if (!imageField.isEmpty()) {
			dish.setImageFile(BlobProxy.generateProxy(imageField.getInputStream(), imageField.getSize()));
			dish.setImage(true);
		}

		dishService.save(dish);

		model.addAttribute("dishId", dish.getId());

		return "redirect:/dishes/"+dish.getId();
	}

	@GetMapping("/editdish/{id}")
	public String editDish(Model model, @PathVariable long id) {

		Optional<Dish> dish = dishService.findById(id);
		if (dish.isPresent()) {
			model.addAttribute("book", dish.get());
			return "editdish";
		} else {
			return "/carta";
		}
	}

	@PostMapping("/editdish")
	public String editDishProcess(Model model, Dish dish, boolean removeImage, MultipartFile imageField)
			throws IOException, SQLException {

		updateImage(dish, removeImage, imageField);

		dishService.save(dish);

		model.addAttribute("dishId", dish.getId());

		return "redirect:/dishes/"+dish.getId();
	}

	private void updateImage(Dish dish, boolean removeImage, MultipartFile imageField) throws IOException, SQLException {
		
		if (!imageField.isEmpty()) {
			dish.setImageFile(BlobProxy.generateProxy(imageField.getInputStream(), imageField.getSize()));
			dish.setImage(true);
		} else {
			if (removeImage) {
				dish.setImageFile(null);
				dish.setImage(false);
			} else {
				// Maintain the same image loading it before updating the book
				Dish dbDish = dishService.findById(dish.getId()).orElseThrow();
				if (dbDish.hasImage()) {
					dish.setImageFile(BlobProxy.generateProxy(dbDish.getImageFile().getBinaryStream(),
							dbDish.getImageFile().length()));
					dish.setImage(true);
				}
			}
		}
	}

}
