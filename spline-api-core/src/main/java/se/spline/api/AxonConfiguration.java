package se.spline.api;

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
import org.axonframework.eventstore.fs.EventFileResolver;
import org.axonframework.eventstore.fs.FileSystemEventStore;
import org.axonframework.eventstore.fs.SimpleEventFileResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Configuration
@ImportResource(value = {"classpath:/basic-config.xml", "classpath*:/entity/*-config.xml"})
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
	public EventStore eventStore() throws IOException {
		final Path tempDir = Files.createTempDirectory("axon_events");
		EventFileResolver resolver = new SimpleEventFileResolver(tempDir.toFile());
		return new FileSystemEventStore(resolver);
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
