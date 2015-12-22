package se.spline.query.repository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Builder;
import org.springframework.data.annotation.Id;
import se.spline.api.repository.RepositoryId;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RepositoryEntity {

    @Id
    @javax.persistence.Id
    private RepositoryId id;
    private String name;


}
