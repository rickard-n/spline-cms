package se.spline.api.repository.builder

import se.spline.api.model.Folder
import se.spline.query.neo4j.folder.FolderEntity
import spock.lang.Specification

class FolderFactoryTest extends Specification {

    def "shouldAddParentIfPresent"() {
        given:
        def entity = FolderEntity.builder().folderId("1").name("folderName").parent(FolderEntity.builder().folderId("2").name("parent").build()).build()
        def expected = Folder.builder().id("1").name("folderName").parent(Folder.builder().id("2").name("parent").build()).build()

        when:
        def folder = FolderFactory.from(entity)

        then:
        expected == folder
    }
}
