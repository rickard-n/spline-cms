package se.spline.api.request.repository;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import se.spline.api.repository.RepositoryMetaData;

@Component
public class RepositoryConverter implements Converter<RepositoryRequest, RepositoryMetaData> {
    @Override
    public RepositoryMetaData convert(RepositoryRequest source) {
        return RepositoryMetaData.builder().name(source.getName()).description(source.getDescription()).build();
    }
}
