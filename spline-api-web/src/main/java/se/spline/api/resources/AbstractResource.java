package se.spline.api.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import se.spline.api.service.ApiService;

@RequestMapping(value = "/api", produces = ResourceConstants.APPLICATION_JSON_CHARSET_UTF_8)
public class AbstractResource {

	@Autowired
	protected ApiService apiService;
}
