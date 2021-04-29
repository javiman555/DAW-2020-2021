package com.trec.rest.controller;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.security.Principal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.trec.model.Dish;
import com.trec.model.Purchase;
import com.trec.model.User;
import com.trec.service.DishService;
import com.trec.service.ImageService;
import com.trec.service.PurchaseService;
import com.trec.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;;

@RestController
@RequestMapping("/api/users")
public class UserRestController {
	
	private static final String POSTS_FOLDER = "posts";
	
	@Autowired
	private UserService userService;
	@Autowired
	private DishService dishService;
	@Autowired
	private PurchaseService purchaseService;
	
	@Autowired
	private ImageService imgService;
	
	@GetMapping("/me")
	public ResponseEntity<User> me(HttpServletRequest request) {
		
		Principal principal = request.getUserPrincipal();
		
		if(principal != null) {
			return ResponseEntity.ok(userService.findByName(principal.getName()).orElseThrow());
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@Operation(summary = "Get all users")
	@ApiResponses(value = {
			 @ApiResponse(
			 responseCode = "200",
			 description = "Found all users",
			 content = {@Content(
			 mediaType = "application/json",
			 schema = @Schema(implementation=User.class)
			 )}
			 ),
			 @ApiResponse(
			responseCode = "403",
			description = "Forbidden. You have to be an admin to do this",
			content = @Content
			), 
			 @ApiResponse(
			 responseCode = "404",
			 description = "Users not found",
			 content = @Content
			 )
			})
	
	@GetMapping("/") // Show all users
	public ResponseEntity<List<User>> findUsers() {
		return ResponseEntity.ok(userService.findAll());
	}
	
	@Operation(summary = "Get a user by its id")
	@ApiResponses(value = {
		 @ApiResponse(
		 responseCode = "200",
		 description = "Found the user",
		 content = {@Content(
		 mediaType = "application/json",
		 schema = @Schema(implementation=User.class)
		 )}
		 ),
		 @ApiResponse(
		 responseCode = "400",
		 description = "Invalid id supplied",
		 content = @Content
		 ), 
		 @ApiResponse(
		responseCode = "403",
		description = "Forbidden. You have to be an admin to do this",
		content = @Content
		), 
		 @ApiResponse(
		 responseCode = "404",
		 description = "User not found",
		 content = @Content
		 )
		})
	
	@GetMapping("/{id}") // Show a user
	public ResponseEntity<User> findUserById(@Parameter(description="id of user to be searched") @PathVariable long id, HttpServletRequest request) {
		
		Principal principal = request.getUserPrincipal();
		String userNameReal = principal.getName();
		Optional<User> userReal = userService.findByName(userNameReal);
		Optional<User> user = userService.findById(id);
		
		if (user.isPresent()) {
			if (userReal.get().getId() == user.get().getId()) {
				
				return ResponseEntity.ok(user.get());
			} else {
				return ResponseEntity.notFound().build();
			}
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@Operation(summary = "Get a user's recomended dishes")
	@ApiResponses(value = {
		 @ApiResponse(
		 responseCode = "200",
		 description = "Found the user's recomended dishes",
		 content = {@Content(
		 mediaType = "application/json",
		 schema = @Schema(implementation=User.class)
		 )}
		 ),
		 @ApiResponse(
		 responseCode = "400",
		 description = "Invalid user id supplied",
		 content = @Content
		 ), 
		 @ApiResponse(
		responseCode = "403",
		description = "Forbidden. You have to be logged as a user to do this",
		content = @Content
		), 
		 @ApiResponse(
		 responseCode = "404",
		 description = "User's recomended dishes not found",
		 content = @Content
		 )
		})
	
	@GetMapping("/{id}/dishes")// Show recomended dishes of the user
	public ResponseEntity<List<Dish>> findRecomendedDishes(@Parameter(description="id of current user") @PathVariable long id, HttpServletRequest request) {
		
		Principal principal = request.getUserPrincipal();
		String userNameReal = principal.getName();
		Optional<User> userReal = userService.findByName(userNameReal);
		Optional<User> user = userService.findById(id);
		
		if (user.isPresent()) {
			if (userReal.get().getId() == user.get().getId()) {
				
				return ResponseEntity.ok(dishService.getRecomended(id));
			} else {
				return ResponseEntity.notFound().build();
			}
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@Operation(summary = "Get a user's current purchase")
	@ApiResponses(value = {
		 @ApiResponse(
		 responseCode = "200",
		 description = "Found the user's current",
		 content = {@Content(
		 mediaType = "application/json",
		 schema = @Schema(implementation=User.class)
		 )}
		 ),
		 @ApiResponse(
		 responseCode = "400",
		 description = "Invalid user id supplied",
		 content = @Content
		 ), 
		 @ApiResponse(
		responseCode = "403",
		description = "Forbidden. You have to be logged as a user to do this",
		content = @Content
		), 
		 @ApiResponse(
		 responseCode = "404",
		 description = "User's current purchase not found",
		 content = @Content
		 )
		})
	
	@GetMapping("/{id}/currentPurchase")// Show the current order of the user
	public ResponseEntity<Purchase> findNewPurchase(@Parameter(description="id of current user")@PathVariable long id, HttpServletRequest request) {
		
		Principal principal = request.getUserPrincipal();
		String userNameReal = principal.getName();
		Optional<User> userReal = userService.findByName(userNameReal);
		Optional<User> user = userService.findById(id);
		
		if (user.isPresent()) {
			if (userReal.get().getId() == user.get().getId()) {
				
				Purchase newpurchase = user.get().getNewPurchase();
				if(newpurchase != null) {
					return ResponseEntity.ok(newpurchase);
				}else {
					return ResponseEntity.notFound().build();
				}
			} else {
				return ResponseEntity.notFound().build();
			}
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@Operation(summary = "Create an empty purchase")
	@ApiResponses(value = {
		 @ApiResponse(
		 responseCode = "201",
		 description = "Empty purchase created correctly",
		 content = {@Content(
		 mediaType = "application/json",
		 schema = @Schema(implementation=User.class)
		 )}
		 ),
		 @ApiResponse(
		 responseCode = "400",
		 description = "Invalid user id supplied",
		 content = @Content
		 ), 
		 @ApiResponse(
		responseCode = "403",
		description = "Forbidden. You have to be logged as a user to do this",
		content = @Content
		), 
		 @ApiResponse(
		 responseCode = "404",
		 description = "Empty purchase couldn't be created",
		 content = @Content
		 )
		})
	
	@PostMapping("/{id}/currentPurchase")//Create an empty newPurchase of the user
	public ResponseEntity<Purchase> newPurchaseProcess(@Parameter(description="id of current user") @PathVariable long id, HttpServletRequest request)  {

		Principal principal = request.getUserPrincipal();
		String userNameReal = principal.getName();
		Optional<User> userReal = userService.findByName(userNameReal);
		Optional<User> user = userService.findById(id);
		
		if (user.isPresent()) {
			if (userReal.get().getId() == user.get().getId()) {
				
				Purchase newPurchase = new Purchase();
				user.get().setNewPurchase(newPurchase);
				userService.save(user.get());
				URI location = fromCurrentRequest().path("").build().toUri();
				return ResponseEntity.created(location).body(newPurchase);
			} else {
				return ResponseEntity.notFound().build();
			}
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@Operation(summary = "Update a current purchase making it final")
	@ApiResponses(value = {
		 @ApiResponse(
		 responseCode = "200",
		 description = "Current purchase has been updated correctly",
		 content = {@Content(
		 mediaType = "application/json",
		 schema = @Schema(implementation=User.class)
		 )}
		 ),
		 @ApiResponse(
		 responseCode = "400",
		 description = "Invalid user id supplied",
		 content = @Content
		 ), 
		 @ApiResponse(
		responseCode = "403",
		description = "Forbidden. You have to be logged as a user to do this",
		content = @Content
		), 
		 @ApiResponse(
		 responseCode = "404",
		 description = "Current purchase couldn't be updated",
		 content = @Content
		 )
		})
	
	@PutMapping("/{id}/currentPurchase")//Move newPurchase of the user the the purchase list
	public ResponseEntity<Purchase> newPurchaseDone(@Parameter(description="id of current user") @PathVariable long id,@Parameter(description="to update a purchase: first name, surname, address, postal code, city, country and phone number") @RequestBody Purchase dataPurchase, HttpServletRequest request)  {

		Principal principal = request.getUserPrincipal();
		String userNameReal = principal.getName();
		Optional<User> userReal = userService.findByName(userNameReal);
		Optional<User> user = userService.findById(id);
		
		if (user.isPresent()) {
			if (userReal.get().getId() == user.get().getId()) {
				
				purchaseService.fillPurchase(dataPurchase,principal,userReal.get());
				
				return ResponseEntity.ok(dataPurchase);
				
			} else {
				return ResponseEntity.notFound().build();
			}
		} else {
			return ResponseEntity.notFound().build();
		}
	}
		
	@Operation(summary = "Update the current purchase of the user to add a dish")
	@ApiResponses(value = {
		 @ApiResponse(
		 responseCode = "200",
		 description = "Current purchase has been updated correctly",
		 content = {@Content(
		 mediaType = "application/json",
		 schema = @Schema(implementation=User.class)
		 )}
		 ),
		 @ApiResponse(
		 responseCode = "400",
		 description = "Invalid user id supplied",
		 content = @Content
		 ), 
		 @ApiResponse(
		responseCode = "403",
		description = "Forbidden. You have to be logged as a user to do this",
		content = @Content
		), 
		 @ApiResponse(
		 responseCode = "404",
		 description = "Current purchase couldn't be updated",
		 content = @Content
		 )
		})
	
	@PutMapping("/{iduser}/currentPurchase/dishes/{iddish}")//Add a dish to the newPurchase of the user
	public ResponseEntity<Purchase> addDish(@Parameter(description="id of current user") @PathVariable long iduser, @Parameter(description="id of dish to be introduced to the current purchase") @PathVariable long iddish, HttpServletRequest request)
			throws IOException, SQLException {
		
		Principal principal = request.getUserPrincipal();
		String userNameReal = principal.getName();
		Optional<User> userReal = userService.findByName(userNameReal);
		Optional<User> user = userService.findById(iduser);
		
		if (user.isPresent()) {
			if (userReal.get().getId() == user.get().getId()) {
				
				Optional<Dish> dish = dishService.findById(iddish);
				if (dish.isPresent()) {
					
					Purchase newPurchase = user.get().getNewPurchase();
					
					newPurchase.getDishes().add(dish.get());
					
					userService.save(user.get());

					return ResponseEntity.ok(newPurchase);

				} else {
					return ResponseEntity.notFound().build();
				}
			} else {
				return ResponseEntity.notFound().build();
			}
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@Operation(summary = "Register a new user without an image")
	@ApiResponses(value = {
		 @ApiResponse(
		 responseCode = "201",
		 description = "The user has been registered correctly",
		 content = {@Content(
		 mediaType = "application/json",
		 schema = @Schema(implementation=Dish.class)
		 )}
		 ),
		 @ApiResponse(
		 responseCode = "400",
		 description = "Invalid form of introducing the data for the new user",
		 content = @Content
		 ), 
		 @ApiResponse(
		 responseCode = "404",
		 description = "User couldn't be created",
		 content = @Content
		 )
		})
	
	@PostMapping("/")//Create User without image
	public ResponseEntity<User> newUserProcess(@Parameter(description="to register a new user: first name, surname, user name, email, phone number and password") @RequestBody User user)  {
		
		List<User> users = userService.findAll();
		List<String> roles = new ArrayList<>();
		boolean exist = userService.existUser(user, users);
		if(exist) {
			
			return ResponseEntity.notFound().build();
		}

		user.setImage(false);
		roles.add("USER");
		user.setRoles(roles);
		
		userService.save(user);
		
		URI location = fromCurrentRequest().path("/{id}")
				.buildAndExpand(user.getId()).toUri();

		return ResponseEntity.created(location).body(user);
	}

	@Operation(summary = "Update the information of the current user (not the image)")
	@ApiResponses(value = {
		 @ApiResponse(
		 responseCode = "200",
		 description = "Information from current user has been updated correctly",
		 content = {@Content(
		 mediaType = "application/json",
		 schema = @Schema(implementation=User.class)
		 )}
		 ),
		 @ApiResponse(
		 responseCode = "400",
		 description = "Invalid user id supplied",
		 content = @Content
		 ), 
		 @ApiResponse(
		responseCode = "403",
		description = "Forbidden. You have to be logged as a user to do this",
		content = @Content
		), 
		 @ApiResponse(
		 responseCode = "404",
		 description = "Information from current user couldn't be updated",
		 content = @Content
		 )
		})
	
	@PutMapping("/{id}")//Change some fields from user (not image)
	public ResponseEntity<User> replaceUser(@Parameter(description="id of current user") @PathVariable long id, @Parameter(description="information of the user to be changed")@RequestBody User user, HttpServletRequest request)
			throws IOException, SQLException {
		
		Principal principal = request.getUserPrincipal();
		String userNameReal = principal.getName();
		Optional<User> userReal = userService.findByName(userNameReal);
		Optional<User> user1 = userService.findById(id);
		
		if (user1.isPresent()) {
			if (userReal.get().getId() == user1.get().getId()) {
				
				if (user != null) {
					
					User olduser = userService.findById(id).get();
					
					User newuser = userService.updateUser(olduser,user);
					
					userService.save(newuser);

					return ResponseEntity.ok(newuser);

				} else {
					return ResponseEntity.notFound().build();
				}
			} else {
				return ResponseEntity.notFound().build();
			}
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@Operation(summary = "Upload the image of a user (only current user can do that)")
	@ApiResponses(value = {
		 @ApiResponse(
		 responseCode = "200",
		 description = "The user image has been uploaded correctly",
		 content = {@Content(
		 mediaType = "application/json",
		 schema = @Schema(implementation=Dish.class)
		 )}
		 ),
		 @ApiResponse(
		 responseCode = "400",
		 description = "Invalid form of introducing the image of the user",
		 content = @Content
		 ), 
		 @ApiResponse(
		responseCode = "403",
		description = "Forbidden. You have to be the current user to do this",
		content = @Content
		), 
		 @ApiResponse(
		 responseCode = "404",
		 description = "User image couldn't be uploaded",
		 content = @Content
		 )
		})
	
	@PostMapping("/{id}/image")//Change image of user
	public ResponseEntity<Object> uploadUserImage(@Parameter(description="id of current user") @PathVariable long id,@Parameter(description="image of current user") @RequestParam MultipartFile imageFile, HttpServletRequest request) throws IOException {

		Principal principal = request.getUserPrincipal();
		String userNameReal = principal.getName();
		Optional<User> userReal = userService.findByName(userNameReal);
		Optional<User> user = userService.findById(id);
		
		if (user.isPresent()) {
			if (userReal.get().getId() == user.get().getId()) {
				
				User user1 = user.get();

				if (user1 != null) {

					URI location = fromCurrentRequest().build().toUri();

					user1.setImageFile(BlobProxy.generateProxy(imageFile.getInputStream(), imageFile.getSize()));
					userService.save(user1);

					imgService.saveImage(POSTS_FOLDER, user1.getId(), imageFile);

					return ResponseEntity.created(location).build();

				} else {
					return ResponseEntity.notFound().build();
				}
				
			} else {
				return ResponseEntity.notFound().build();
			}
		} else {
			return ResponseEntity.notFound().build();
		}
	}
		
	@Operation(summary = "Download image of current user")
	@ApiResponses(value = {
		 @ApiResponse(
		 responseCode = "200",
		 description = "Current user image has been downloaded correctly",
		 content = {@Content(
		 mediaType = "application/json",
		 schema = @Schema(implementation=Dish.class)
		 )}
		 ),
		 @ApiResponse(
		 responseCode = "404",
		 description = "Current user image couldn't be downloaded",
		 content = @Content
		 )
		})
	
	@GetMapping("/{id}/image")//show image of user
	public ResponseEntity<Object> downloadUserImage(@Parameter(description="id of current user") @PathVariable long id, HttpServletRequest request) throws MalformedURLException {
		
		Principal principal = request.getUserPrincipal();
		String userNameReal = principal.getName();
		Optional<User> userReal = userService.findByName(userNameReal);
		Optional<User> user = userService.findById(id);
		
		if (user.isPresent()) {
			if (userReal.get().getId() == user.get().getId()) {
				User user1 = user.get();
				
				if(user1 != null) {
					return this.imgService.createResponseFromImage(POSTS_FOLDER, id);
				} else {
					return ResponseEntity.notFound().build();
				}
			} else {
				return ResponseEntity.notFound().build();
			}
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@Operation(summary = "Delete image of current user (only current user can do that)")
	@ApiResponses(value = {
		 @ApiResponse(
		 responseCode = "204",
		 description = "No content. Current user image has been deleted correctly",
		 content = {@Content(
		 mediaType = "application/json",
		 schema = @Schema(implementation=Dish.class)
		 )}
		 ),
		 @ApiResponse(
		responseCode = "400",
	    description = "Invalid user id supplied",
		content = @Content
		), 
		@ApiResponse(
	    responseCode = "403",
		description = "Forbidden. You have to be the current user to do this",
		content = @Content
		), 
		 @ApiResponse(
		 responseCode = "404",
		 description = "Current user image couldn't be deleted",
		 content = @Content
		 )
		})
	
	@DeleteMapping("/{id}/image")//delete image of user
	public ResponseEntity<Object> deleteUserImage(@Parameter(description="id of current user") @PathVariable long id, HttpServletRequest request) throws IOException {

		Principal principal = request.getUserPrincipal();
		String userNameReal = principal.getName();
		Optional<User> userReal = userService.findByName(userNameReal);
		Optional<User> user = userService.findById(id);
		
		if (user.isPresent()) {
			if (userReal.get().getId() == user.get().getId()) {
				
				User user1 = user.get();
				
				if(user1 != null) {
					
					user1.setImageFile(null);
					userService.save(user1);
					
					this.imgService.deleteImage(POSTS_FOLDER, id);
					
					return ResponseEntity.noContent().build();
					
				} else {
					return ResponseEntity.notFound().build();
				}
			} else {
				return ResponseEntity.notFound().build();
			}
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
