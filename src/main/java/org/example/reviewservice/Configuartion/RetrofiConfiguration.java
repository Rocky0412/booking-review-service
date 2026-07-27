package org.example.reviewservice.Configuartion;

import com.netflix.discovery.EurekaClient;
import okhttp3.OkHttpClient;
import org.example.reviewservice.api.LocationServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

@Configuration
public class RetrofiConfiguration {
    @Autowired
    private EurekaClient eurekaClient;

    public String getServiceUrl(String serviceName) {
        return eurekaClient.getNextServerFromEureka(serviceName,false).getHomePageUrl();
    }
    @Bean
    public LocationServiceAPI getLocationServiceAPI() {
        return new Retrofit
                .Builder()
                .baseUrl(getServiceUrl("RIDERLOCATIONSERVICE"))
                .addConverterFactory(JacksonConverterFactory.create())
                .client( new OkHttpClient())
                .build()
                .create(LocationServiceAPI.class);
    }
}
