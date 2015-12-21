package se.spline.entity.type;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;
import se.spline.api.type.TypeId;
import se.spline.api.type.command.CreateTypeCommand;
import se.spline.api.type.event.TypeCreatedEvent;

public class Type extends AbstractAnnotatedAggregateRoot<TypeId> {

	@AggregateIdentifier
	private TypeId id;

	// No-arg constructor, required by Axon
	protected Type() {
	}

	@CommandHandler
	public Type(CreateTypeCommand command) {
		apply(new TypeCreatedEvent(new TypeId(), command.getName()));
	}

	@EventSourcingHandler
	public void on(TypeCreatedEvent event) {
		this.id = event.getTypeId();
	}
}
