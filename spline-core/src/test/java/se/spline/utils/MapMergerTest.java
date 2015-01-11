package se.spline.utils;

import com.google.common.collect.ImmutableMap;
import org.junit.Test;

import java.util.Map;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public class MapMergerTest {

	@Test
	public void shouldMergeMaps() {
		Map<String, Integer> map1 = ImmutableMap.of("a", 2, "b", 3);
		Map<String, Integer> map2 = ImmutableMap.of("a", 3, "c", 4);

		final Map<String, Integer> mergedMap = MapMerger.mergeMaps(Stream.of(map1, map2));
		final ImmutableMap<String, Integer> expected = ImmutableMap.of("a", 3, "b", 3, "c", 4);

		assertEquals(expected, mergedMap);
	}
}