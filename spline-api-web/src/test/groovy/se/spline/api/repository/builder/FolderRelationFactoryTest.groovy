package se.spline.api.repository.builder

import se.spline.api.model.Folder
import se.spline.query.neo4j.folder.FolderEntity
import spock.lang.Specification

class FolderRelationFactoryTest extends Specification {
    def "shouldCreateFolderFromEntity"() {
        given:
        def entity = FolderEntity.builder().name("folderName").build()
        when:
        def folder = FolderRelationFactory.from(entity)
        then:
        Folder.builder().name("folderName").build() == folder
    }
}
