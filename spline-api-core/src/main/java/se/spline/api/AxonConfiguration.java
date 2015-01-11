package se.spline.api;

import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.SimpleCommandBus;
import org.axonframework.commandhandling.annotation.AnnotationCommandHandlerBeanPostProcessor;
import org.axonframework.commandhandling.gateway.CommandGatewayFactoryBean;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.SimpleEventBus;
import org.axonframework.eventhandling.annotation.AnnotationEventListenerBeanPostProcessor;
import org.axonframework.eventstore.EventStore;
import org.axonframework.eventstore.fs.EventFileResolver;
import org.axonframework.eventstore.fs.FileSystemEventStore;
import org.axonframework.eventstore.fs.SimpleEventFileResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Configuration
public class AxonConfiguration {


	@Bean
	public CommandGatewayFactoryBean commandGateway() {
		CommandGatewayFactoryBean factory = new CommandGatewayFactoryBean();
		factory.setCommandBus(commandBus());
		return factory;
	}

	@Bean
	public CommandBus commandBus() {
		return new SimpleCommandBus();
	}

	@Bean
	AnnotationCommandHandlerBeanPostProcessor annotationCommandHandlerBeanPostProcessor() {
		AnnotationCommandHandlerBeanPostProcessor handler = new AnnotationCommandHandlerBeanPostProcessor();
		handler.setCommandBus(commandBus());
		return handler;
	}

	@Bean
	public EventStore eventStore() throws IOException {
		final Path tempDir = Files.createTempDirectory("axon_events");
		EventFileResolver resolver = new SimpleEventFileResolver(tempDir.toFile());
		return new FileSystemEventStore(resolver);
	}

	@Bean
	public EventBus eventBus() {
		return new SimpleEventBus();
	}

	@Bean
	AnnotationEventListenerBeanPostProcessor annotationEventListenerBeanPostProcessor() {
		AnnotationEventListenerBeanPostProcessor listener = new AnnotationEventListenerBeanPostProcessor();
		listener.setEventBus(eventBus());
		return listener;
	}
}
