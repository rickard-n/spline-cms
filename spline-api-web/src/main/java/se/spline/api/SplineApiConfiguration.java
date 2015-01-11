package se.spline.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import se.spline.api.service.ApiService;
import se.spline.api.service.ApiServiceImpl;

@Configuration
public class SplineApiConfiguration {

	@Bean
	ApiService apiService() {
		return new ApiServiceImpl();
	}
}
