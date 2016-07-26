package se.spline.api.type.entity;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;
import se.spline.api.type.BaseType;
import se.spline.api.type.TypeId;
import se.spline.api.type.command.CreateTypeCommand;
import se.spline.api.type.event.TypeCreatedEvent;
import se.spline.api.type.property.PropertyType;

import java.util.List;

public class Type extends AbstractAnnotatedAggregateRoot<TypeId> {

	@AggregateIdentifier
	private TypeId id;

    private String name;
    private String displayName;
    private String description;

    private BaseType baseType;
    private TypeId parent;

    private boolean creatable;
    private List<PropertyType> properties;


    @SuppressWarnings("UnusedDeclaration")
	protected Type() {
	}

	@CommandHandler
	public Type(CreateTypeCommand command) {
	    this.id = command.getId();
		apply(new TypeCreatedEvent(command.getId(), command.getName(), command.getBaseType(), command.getProperties()));
	}

    @Override
    public TypeId getIdentifier() {
        return this.id;
    }

    @EventSourcingHandler
	public void on(TypeCreatedEvent event) {
		this.id = event.getTypeId();
        this.name = event.getName();
        this.baseType = event.getBaseType();
        this.properties = event.getProperties();
    }
}
