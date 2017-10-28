package fr.maed.website.kwizzy.esorm;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

/**
 * Created by alexis on 25/10/17.
 * French author.
 */
public class QueryMoreLikeThis extends QueryBuilder
{

    public QueryMoreLikeThis(List<ElasticDto> d, String... fields)
    {
        JSONObject m = getJson();
        JSONObject jo = new JSONObject();
        JSONArray ja = new JSONArray();
        d.forEach(e -> ja.put(e.toMultiGetFormat()));
        jo.put("like", ja);
        m.put("more_like_this", jo);
        if (fields.length > 0)
            m.put("fields", Arrays.asList(fields));
    }

}
