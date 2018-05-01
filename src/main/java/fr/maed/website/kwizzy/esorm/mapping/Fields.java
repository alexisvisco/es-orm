package fr.maed.website.kwizzy.esorm.mapping;

import org.json.JSONObject;

import java.util.Arrays;
import java.util.function.Consumer;

public class Fields extends JSONObject {

    public Fields setType(String name, Type type, Consumer<Parameter>... params) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", type);
        Arrays.stream(params).forEach(e -> e.accept(new Parameter(jsonObject)));
        put(name, jsonObject);
        return this;
    }
}
