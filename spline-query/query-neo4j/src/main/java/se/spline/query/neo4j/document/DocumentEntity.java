package se.spline.query.neo4j.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Builder;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@NodeEntity(label = "Document")
public class DocumentEntity {
    @GraphId
    private Long id;

    private String documentId;

    private String name;
}
