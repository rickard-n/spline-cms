package se.spline;

import org.apache.catalina.filters.CorsFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfiguration {

	@Bean
	public CorsFilter corsFilter() {
		return new CorsFilter();
	}
}
