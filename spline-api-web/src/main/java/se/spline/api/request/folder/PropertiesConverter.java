package se.spline.api.request.folder;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class PropertiesConverter implements Converter<PropertiesRequest, Map<String,String>> {
	@Override
	public Map<String, String> convert(PropertiesRequest source) {
		final Map<String, String> map = new HashMap<>(source.getProperties().size());
		for(PropertyRequest property : source.getProperties()) {
			map.put(property.getName(), property.getValue());
		}
		return map;
	}
}
