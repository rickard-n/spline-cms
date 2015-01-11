package se.spline;

import com.mangofactory.swagger.annotations.ApiIgnore;
import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.models.dto.ApiInfo;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@EnableSwagger
@Configuration
public class SwaggerConfiguration {

	@Autowired
	private SpringSwaggerConfig springSwaggerConfig;

	@Bean
	public SwaggerSpringMvcPlugin customImplementation(){
		return new SwaggerSpringMvcPlugin(this.springSwaggerConfig)
				.apiInfo(apiInfo()).includePatterns("/api.*");
	}

	@ApiIgnore
	@Controller
	public static class StaticRoutes {
		@Autowired
		private Environment environment;

		@RequestMapping("/")
		public String redirectToSwagger(ModelMap model) {
			model.put("protocol", environment.getProperty("spring.application.protocol", "http"));
			model.put("hostname", environment.getProperty("spring.application.hostname", "localhost"));
			model.put("port", environment.getProperty("spring.application.port", environment.getProperty("server.port")));
			return "index";
		}
	}

	private ApiInfo apiInfo() {
		return new ApiInfo(
				"Spline CMS API",
				"Spline CMS API.",
				null,
				"rickard.andersson@svt.se",
				null,
				null
		);
	}
}
