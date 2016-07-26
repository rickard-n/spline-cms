package se.spline.query.neo4j.type;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import se.spline.api.type.BaseType;

import java.util.ArrayList;
import java.util.List;

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

    @Singular
    @Relationship()
    private List<TypePropertyEntity> properties = new ArrayList<>();
}
