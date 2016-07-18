package se.spline.api.repository;

import io.katharsis.queryParams.QueryParams;
import io.katharsis.repository.RelationshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.spline.api.model.Folder;
import se.spline.api.model.Property;
import se.spline.query.neo4j.folder.FolderQueryRepository;

@Component
public class FolderToPropertyRepository implements RelationshipRepository<Folder, String, Property, String> {

    @Autowired
    private FolderQueryRepository folderQueryRepository;

    @Override
    public void setRelation(Folder source, String targetId, String fieldName) {

    }

    @Override
    public void setRelations(Folder source, Iterable<String> targetIds, String fieldName) {

    }

    @Override
    public void addRelations(Folder source, Iterable<String> targetIds, String fieldName) {

    }

    @Override
    public void removeRelations(Folder source, Iterable<String> targetIds, String fieldName) {

    }

    @Override
    public Property findOneTarget(String sourceId, String fieldName, QueryParams queryParams) {
        return null;
    }

    @Override
    public Iterable<Property> findManyTargets(String sourceId, String fieldName, QueryParams queryParams) {
        return null;
    }
}
