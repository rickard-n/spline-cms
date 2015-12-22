package se.spline.query;

import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import se.spline.query.folder.FolderQueryRepository;
import se.spline.query.repository.RepositoryQueryRepository;

@EnableMongoRepositories(mongoTemplateRef = "mongoBOTemplate",
    basePackageClasses = {FolderQueryRepository.class, RepositoryQueryRepository.class})
@Configuration
public class MongoConfiguration {

    @Value("${mongo.hostQuery}")
    private String hostBO;

    @Value("${mongo.databaseQuery}")
    private String databaseBO;

    @Bean
    public MongoDbFactory mongoBODbFactory() throws Exception {
        return new SimpleMongoDbFactory(new MongoClient(hostBO), databaseBO);
    }

    @Bean
    public MongoTemplate mongoBOTemplate() throws Exception {
        return new MongoTemplate(mongoBODbFactory());
    }
}
