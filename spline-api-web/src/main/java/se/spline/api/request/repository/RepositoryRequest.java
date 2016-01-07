package se.spline.api.request.repository;

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
@JsonDeserialize(builder = RepositoryRequest.RepositoryRequestBuilder.class)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@EqualsAndHashCode
public class RepositoryRequest {

    private final String name;
    private String description;

    @JsonPOJOBuilder(withPrefix = "")
    public static final class RepositoryRequestBuilder {

    }
}
