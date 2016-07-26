package se.spline.query.neo4j;

import org.neo4j.ogm.session.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableNeo4jRepositories(basePackages = "se.spline.query.neo4j")
@EnableTransactionManagement
public class SplineNeo4jConfiguration extends Neo4jConfiguration {

    @Value("${neo4j.host}")
    private String host;
    @Value("${neo4j.username}")
    private String username;
    @Value("${neo4j.password}")
    private String password;


    @Bean
    public org.neo4j.ogm.config.Configuration getConfiguration() {
        org.neo4j.ogm.config.Configuration config = new org.neo4j.ogm.config.Configuration();
        config
            .driverConfiguration()
            //.setDriverClassName("org.neo4j.ogm.drivers.http.driver.HttpDriver")
            //.setConnectionPoolSize(2)
            .setCredentials(username, password)
            .setURI("http://" + host);
        return config;
    }

    @Override
    public SessionFactory getSessionFactory() {
        return new SessionFactory(getConfiguration(), "se.spline.query.neo4j");
    }
}
