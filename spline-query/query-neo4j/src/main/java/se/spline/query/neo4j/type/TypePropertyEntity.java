package se.spline.query.neo4j.type;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity(label = "TypeProperty")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TypePropertyEntity {
    @GraphId
    private Long id;

}
