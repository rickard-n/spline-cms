package se.spline.query.neo4j.type;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Builder;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import se.spline.api.type.BaseType;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@NodeEntity(label = "Type")
public class TypeEntity {
    @GraphId
    private Long id;

    private String typeId;

    private String name;

    private BaseType baseType;
}
