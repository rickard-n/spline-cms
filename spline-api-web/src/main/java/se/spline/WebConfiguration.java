package se.spline;

import org.apache.catalina.filters.CorsFilter;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfiguration {

	@Bean
	public FilterRegistrationBean corsFilter() {
		final FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new CorsFilter());
		filterRegistrationBean.addInitParameter(CorsFilter.PARAM_CORS_ALLOWED_METHODS, "GET,POST,HEAD,OPTIONS,DELETE");
		return filterRegistrationBean;
	}

}
