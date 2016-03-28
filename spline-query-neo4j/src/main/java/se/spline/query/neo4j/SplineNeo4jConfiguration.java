package se.spline.query.neo4j;

import org.neo4j.ogm.session.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.server.Neo4jServer;
import org.springframework.data.neo4j.server.RemoteServer;
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

    @Override
    public Neo4jServer neo4jServer() {
        return new RemoteServer("http://" + host, username, password);
    }

    @Override
    public SessionFactory getSessionFactory() {
        return new SessionFactory("se.spline.query.neo4j");
    }
}
