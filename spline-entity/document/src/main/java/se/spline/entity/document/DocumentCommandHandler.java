package se.spline.entity.document;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.spline.api.document.CreateDocumentCommand;

@Component
public class DocumentCommandHandler {

	private Repository<Document> repository;

	@CommandHandler(commandName = "createDocumentCommand")
	public void handleCreateFolder(CreateDocumentCommand command) {
		Document document = new Document(command.getDocumentId(),
				command.getName());
		repository.add(document);
	}

	@Autowired
	public void setRepository(Repository<Document> repository) {
		this.repository = repository;
	}
}
