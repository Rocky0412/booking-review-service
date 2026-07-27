package org.example.reviewservice.api;

import org.example.reviewservice.RequestDTO.LocationDTO;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

import java.util.List;

public interface LocationServiceAPI {

   @POST("/api/v1/location/nearbyDriver")
    Call<List<Long>> getNearByDriver(@Body LocationDTO locationDTO);
}
