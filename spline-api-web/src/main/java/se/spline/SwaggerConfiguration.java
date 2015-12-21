package se.spline;

import org.springframework.context.annotation.Configuration;

//@EnableSwagger
@Configuration
public class SwaggerConfiguration {
    /*
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
	} */
}
