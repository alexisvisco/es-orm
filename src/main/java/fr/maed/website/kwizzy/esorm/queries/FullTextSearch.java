package fr.maed.website.kwizzy.esorm.queries;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.Arrays;

/**
 * Created by alexis on 25/10/17.
 * French author.
 */
public class FullTextSearch extends Query
{
    public FullTextSearch(String value, String... fields)
    {
        obj.getJSONObject("multi_match").put("query", value);
        if (fields.length > 0)
            obj.getJSONObject("multi_match").put("fields", new JSONArray(Arrays.asList(fields)));
        obj.getJSONObject("multi_match").put("fuzziness", "AUTO");
        obj.getJSONObject("multi_match").put("prefix_length", 2);
    }

    public FullTextSearch setFields(String... fields)
    {
        if (fields.length > 0)
            obj.getJSONObject("multi_match").put("fields", new JSONArray(Arrays.asList(fields)));
        return this;
    }

    public FullTextSearch setType(Object type)
    {
        obj.getJSONObject("multi_match").put("type", type);
        return this;
    }

    public FullTextSearch setFuzziness(Object value)
    {
        obj.getJSONObject("multi_match").put("fuzziness", value);
        return this;
    }

    public FullTextSearch setAnalyser(Object value)
    {
        obj.getJSONObject("multi_match").put("analyser", value);
        return this;
    }

    public FullTextSearch setPrefixLenght(Object value)
    {
        obj.getJSONObject("multi_match").put("prefix_length", 2);
        return this;
    }

    @Override
    void init()
    {
        obj.put("multi_match", new JSONObject());
    }
}
