package fr.maed.website.kwizzy.esorm.mapping;

import org.json.JSONObject;

import java.util.Arrays;
import java.util.function.Consumer;

public class Mapping extends JSONObject {

    private JSONObject properties = new JSONObject();

    public Mapping(String collection) {
        put("mappings", new JSONObject().put(collection, properties));
    }

    /**
     * This method add a field type with optional parameters.
     *
     * @param field that have the type.
     * @param type is the type of the field.
     * @param parameters optional parameters.
     * @return this.
     */
    public Mapping setType(String field, Type type, Consumer<JSONObject>... parameters) {
        JSONObject fieldProperty = new JSONObject();
        fieldProperty.put("type", type.value);
        Arrays.stream(parameters).forEach(e -> e.accept(fieldProperty));
        properties.put(field, fieldProperty);
        return this;
    }
}
