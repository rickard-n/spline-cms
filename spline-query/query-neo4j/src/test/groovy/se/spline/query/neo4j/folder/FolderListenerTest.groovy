package se.spline.query.neo4j.folder

import org.joda.time.DateTime
import se.spline.api.folder.FolderId
import se.spline.api.folder.event.FolderCreatedEvent
import spock.lang.Specification

class FolderListenerTest extends Specification {

    def "shouldCreateFolderWithoutParent"() {
        given:
        def repository = Mock(FolderQueryRepository)
        def listener = new FolderListener(repository)
        def name = "repository"
        def timestamp = new DateTime()
        def folderId = FolderId.from("1")

        when:
        listener.handleFolderCreatedEvent(new FolderCreatedEvent(folderId, name, null), timestamp)

        then:
        1 * repository.save(FolderEntity.builder()
            .folderId(folderId.identifier)
            .name(name)
            .created(timestamp.toDate())
            .updated(timestamp.toDate())
            .build())
    }

    def "shouldAddFolderAsChildToParentNode"() {
        given:
        def repository = Mock(FolderQueryRepository)
        def parent = FolderEntity.builder().folderId("1").build()
        repository.findByFolderId(parent.folderId) >> parent

        def listener = new FolderListener(repository)

        def timestamp = new DateTime()
        def folderId = FolderId.from("2")
        def name = "child"
        def childEntity = FolderEntity.builder()
            .folderId(folderId.identifier)
            .name(name)
            .created(timestamp.toDate())
            .updated(timestamp.toDate())
            .parent(parent)
            .build()
        repository.save(childEntity) >> childEntity



        when:
        listener.handleFolderCreatedEvent(new FolderCreatedEvent(folderId, name, FolderId.from(parent.folderId)), timestamp)


        then:
        1 * repository.save(childEntity)
    }
}
