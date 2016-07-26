package se.spline;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import se.spline.api.jackson.deser.TypePropertyDeserializer;
import se.spline.api.type.property.PropertyType;

@Configuration
@Import(io.katharsis.spring.boot.KatharsisConfigV2.class)
public class KatharsisConfig {

    @Autowired
    private ObjectMapper objectMapper;

    //@Bean
    Module extendedJsonModule() {
        SimpleModule simpleModule = new SimpleModule("EXTENDED_JSON",
            new Version(1, 0, 0, null, null, null));

        simpleModule.addDeserializer(PropertyType.class, new TypePropertyDeserializer(objectMapper));
        objectMapper.registerModule(simpleModule);
        return simpleModule;
    }

}
