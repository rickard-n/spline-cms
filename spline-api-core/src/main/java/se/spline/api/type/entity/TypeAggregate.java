package se.spline.api.type.entity;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcedMember;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;
import se.spline.api.type.command.CreateTypeCommand;
import se.spline.api.type.event.TypeCreatedEvent;
import se.spline.domain.Type;

public class TypeAggregate extends AbstractAnnotatedAggregateRoot<String> {

	@AggregateIdentifier
	private String id;

    @EventSourcedMember
    private TypeAggregate type;

    @SuppressWarnings("UnusedDeclaration")
	protected TypeAggregate() {
	}

	@CommandHandler
	public TypeAggregate(CreateTypeCommand command) {
	    this.id = command.getId().getIdentifier();
		apply(new TypeCreatedEvent(
		    command.getId(),
            command.getName(),
            command.getDisplayName(),
            command.getDescription(),
            command.getBaseType(),
            command.getParent(),
            command.isCreatable(),
            command.isFulltextIndexed(),
            command.getProperties()));
	}

    @Override
    public String getIdentifier() {
        return this.id;
    }

    @EventSourcingHandler
	public void on(TypeCreatedEvent event) {
		this.id = event.getTypeId().getIdentifier();
        Type.builder()
            .id(event.getTypeId())
            .name(event.getName())
            .displayName(event.getDisplayName())
            .description(event.getDescription())
            .baseType(event.getBaseType())
            .parent(event.getTypeId())
            .creatable(event.isCreatable())
            .fulltextIndexed(event.isFulltextIndexed())
            .properties(event.getProperties()).build();
    }
}
