package se.spline.api;

import com.mongodb.Mongo;
import com.mongodb.ServerAddress;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.CommandDispatchInterceptor;
import org.axonframework.commandhandling.CommandHandlerInterceptor;
import org.axonframework.commandhandling.SimpleCommandBus;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.commandhandling.gateway.CommandGatewayFactoryBean;
import org.axonframework.commandhandling.interceptors.BeanValidationInterceptor;
import org.axonframework.commandhandling.interceptors.LoggingInterceptor;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.SimpleEventBus;
import org.axonframework.eventstore.EventStore;
import org.axonframework.eventstore.mongo.DefaultMongoTemplate;
import org.axonframework.eventstore.mongo.MongoEventStore;
import org.axonframework.eventstore.mongo.MongoTemplate;
import org.axonframework.saga.SagaRepository;
import org.axonframework.saga.repository.mongo.MongoSagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Configuration
public class AxonConfiguration {


	@Bean
	@Autowired
	public CommandGatewayFactoryBean<CommandGateway> commandGateway(CommandBus commandBus) {
		CommandGatewayFactoryBean<CommandGateway> factory = new CommandGatewayFactoryBean<>();
		factory.setCommandBus(commandBus);
		return factory;
	}

	@Bean
	public CommandBus commandBus() {

		final SimpleCommandBus commandBus = new SimpleCommandBus();
		List<CommandDispatchInterceptor> interceptors = new ArrayList<>(1);
		interceptors.add(new BeanValidationInterceptor());
		commandBus.setDispatchInterceptors(interceptors);
		List<CommandHandlerInterceptor> commandHandlerInterceptors = new ArrayList<>(1);
		commandHandlerInterceptors.add(new LoggingInterceptor("se.spline.CommandBus"));
		commandBus.setHandlerInterceptors(commandHandlerInterceptors);
		return commandBus;
	}
	/*
	@Bean
	AnnotationCommandHandlerBeanPostProcessor annotationCommandHandlerBeanPostProcessor() {
		AnnotationCommandHandlerBeanPostProcessor handler = new AnnotationCommandHandlerBeanPostProcessor();
		handler.setCommandBus(commandBus());
		return handler;
	}
    */

    @Bean
    public MongoTemplate eventMongoTemplate() throws UnknownHostException {
        final DefaultMongoTemplate defaultMongoTemplate = new DefaultMongoTemplate(mongo());

        return defaultMongoTemplate;
    }

    private Mongo mongo() throws UnknownHostException {
        return new Mongo(Collections.singletonList(new ServerAddress("192.168.99.100", 27017)));
    }

    @Bean
	public EventStore eventStore() throws IOException {
	    return new MongoEventStore(eventMongoTemplate());
    }

    @Bean
    public SagaRepository mongoSagaRepository() throws UnknownHostException {
        return new MongoSagaRepository(new org.axonframework.saga.repository.mongo.DefaultMongoTemplate(mongo()));
    }

    @Bean
	public EventBus eventBus() {
		return new SimpleEventBus();
	}
    /*
	@Bean
	AnnotationEventListenerBeanPostProcessor annotationEventListenerBeanPostProcessor() {
		AnnotationEventListenerBeanPostProcessor listener = new AnnotationEventListenerBeanPostProcessor();
		listener.setEventBus(eventBus());
		return listener;
	} */
}
