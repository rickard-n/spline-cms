package se.spline.api.repository.builder;

import se.spline.api.model.Document;
import se.spline.query.neo4j.document.DocumentEntity;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class DocumentFactory {

    public static Document from(DocumentEntity entity) {
        return Document.builder()
            .id(entity.getDocumentId())
            .name(entity.getName())
            .build();
    }

    public static List<Document> from(Iterable<DocumentEntity> entities) {
        return StreamSupport.stream(entities.spliterator(), false)
            .map(DocumentFactory::from)
            .collect(Collectors.toList());
    }
}
