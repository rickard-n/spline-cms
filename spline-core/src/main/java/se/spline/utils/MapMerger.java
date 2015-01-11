package se.spline.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class MapMerger {

	public static <K, V> Map<K, V> mergeMaps(Stream<? extends Map<K, V>> stream) {
		return stream.collect(HashMap::new, Map::putAll, Map::putAll);
	}
}
