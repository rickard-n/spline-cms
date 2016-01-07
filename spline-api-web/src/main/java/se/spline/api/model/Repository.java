package se.spline.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Builder;

@Builder
@Getter
@ToString
@JsonDeserialize(builder = Repository.RepositoryBuilder.class)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@EqualsAndHashCode
public class Repository {
    private final String id;
    private final String name;
    private final String folderId;


    @JsonPOJOBuilder(withPrefix = "")
    public static final class RepositoryBuilder {

    }

}
