package org.example.reviewservice.Controllers;


import org.example.reviewservice.RequestDTO.LocationDTO;
import org.example.reviewservice.api.LocationServiceAPI;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import retrofit2.Call;
import retrofit2.http.POST;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/booking")
public class BookingController {

    private final LocationServiceAPI locationService;

    public BookingController(LocationServiceAPI locationService) {
        this.locationService = locationService;
    }

    @PostMapping("/getDriver")
    public ResponseEntity<List<Long>> getDriver(@RequestBody LocationDTO locationDTO) throws IOException {

        Call<List<Long>> driver= locationService.getNearByDriver(locationDTO);
        return ResponseEntity.ok(driver.execute().body());
    }


}
