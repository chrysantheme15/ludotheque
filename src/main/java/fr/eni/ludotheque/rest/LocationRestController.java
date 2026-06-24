package fr.eni.ludotheque.rest;

import fr.eni.ludotheque.bo.Location;
import fr.eni.ludotheque.dto.LocationDTO;
import fr.eni.ludotheque.exceptions.DataNotFound;
import fr.eni.ludotheque.service.LocationService;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/locations")
public class LocationRestController {

    @NonNull
    private final LocationService locationService;

    public LocationRestController(LocationService locationService) {
        this.locationService = locationService;
    }

    @PostMapping
    public ResponseEntity<?> ajouterLocation(
            @RequestBody LocationDTO locationDto) {

        try {

            Location location =
                    locationService.ajouterLocation(locationDto);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(location);

        } catch (DataNotFound e) {

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());

        } catch (RuntimeException e) {

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }
}
