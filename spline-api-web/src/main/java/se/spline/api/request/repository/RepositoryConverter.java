package se.spline.api.request.repository;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import se.spline.api.repository.RepositoryId;
import se.spline.query.repository.RepositoryEntity;

@Component
public class RepositoryConverter implements Converter<RepositoryRequest, RepositoryEntity> {
    @Override
    public RepositoryEntity convert(RepositoryRequest source) {
        return RepositoryEntity.builder().id(new RepositoryId()).name(source.getName()).build();
    }
}
