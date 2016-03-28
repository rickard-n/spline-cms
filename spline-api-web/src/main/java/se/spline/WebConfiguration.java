package se.spline;

import org.apache.catalina.filters.CorsFilter;
import org.neo4j.ogm.session.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.template.Neo4jOperations;

import java.util.Collections;

@Configuration
public class WebConfiguration {

	@Bean
	public FilterRegistrationBean corsFilter() {
		final FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new CorsFilter());
		filterRegistrationBean.addInitParameter(CorsFilter.PARAM_CORS_ALLOWED_METHODS, "GET,POST,PATCH,DELETE");
		return filterRegistrationBean;
	}

    @Bean
    @Autowired
    public HealthIndicator neo4jHealthIndicator(Neo4jOperations neo4jTemplate) {
        return () -> {
            final Health.Builder builder = new Health.Builder();
            builder.up().withDetail("database", "Neo4j");
            try {
                final Result result = neo4jTemplate.query("start n=node(*) match n return count(n)", Collections.emptyMap());
                builder.withDetail("result", result.queryResults().iterator().next().get("count(n)"));
            } catch(Exception ex) {
                builder.down(ex);
            }
            return builder.build();
        };

    }
}
