package com.chatop.estate.controller;

import com.chatop.estate.dto.RentalDto;
import com.chatop.estate.service.RentalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {

    private final RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @Operation(
            summary = "Get all rentals",
            description = "Allows a user to get all rentals."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All rentals"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @GetMapping("")
    public List<RentalDto> getAllRentals(){
        return rentalService.getAllRentals();
    }

    @Operation(
            summary = "Get a rental",
            description = "Allows a user to get a rental."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "One rental"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @GetMapping("/{id}")
    public RentalDto getRental(@PathVariable UUID id){
        return rentalService.getRental(id);
    }

    @Operation(
            summary = "Post a rental",
            description = "Allows a user to post a rental."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rental created"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @PostMapping("/{id}")
    public String postRental(@PathVariable("id") UUID id,
                             @RequestParam("name") String name,
                             @RequestParam("surface") Double surface,
                             @RequestParam("price") Double price,
                             @RequestParam("description") String description,
                             @RequestParam("picture") MultipartFile picture){
        return rentalService.postRental(id, name, surface, price, description, picture);
    }

    @Operation(
            summary = "Update a rental",
            description = "Allows a user to update his rental."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rental updated"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @PutMapping("/{id}")
    public String updateRental(@PathVariable("id") UUID id,
                               @RequestPart(value="name", required = false) String name,
                               @RequestPart(value="surface", required = false) Double surface,
                               @RequestPart(value="price", required = false) Double price,
                               @RequestPart(value="description", required = false) String description,
                               @RequestPart(value="picture", required = false) MultipartFile picture){
        return rentalService.updateRental(id, name, surface, price, description, picture);
    }
}
