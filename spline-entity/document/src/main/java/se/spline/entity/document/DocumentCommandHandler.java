package se.spline.entity.document;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import se.spline.api.document.command.CreateDocumentCommand;

//@Component
public class DocumentCommandHandler {

	private Repository<Document> repository;

	@Autowired
	public void setRepository(Repository<Document> repository) {
		this.repository = repository;
	}

    @CommandHandler
    public void handleCreateFolder(CreateDocumentCommand command) {
        Document document = new Document(command);
        repository.add(document);
    }
}
