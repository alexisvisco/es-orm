package fr.maed.website.kwizzy.esorm;

import fr.maed.website.kwizzy.esorm.util.MultiGetFormat;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;

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

    public QueryMoreLikeThis(List<String> ids, String database, String collection, String... fields)
    {
        JSONObject m = getJson();
        JSONObject jo = new JSONObject();
        JSONArray ja = new JSONArray();
        ids.forEach(e -> ja.put(new MultiGetFormat(e, collection, database).toJson()));
        jo.put("like", ja);
        m.put("more_like_this", jo);
        if (fields.length > 0)
            m.getJSONObject("more_like_this").put("fields", Arrays.asList(fields));
    }

    public QueryMoreLikeThis(Collection<JSONObject> customDatas, String... fields)
    {
        JSONObject m = getJson();
        JSONObject jo = new JSONObject();
        JSONArray ja = new JSONArray();
        customDatas.forEach(e -> ja.put(customDatas));
        jo.put("like", ja);
        m.put("more_like_this", jo);
        if (fields.length > 0)
            m.put("fields", Arrays.asList(fields));
    }

    public QueryMoreLikeThis minTermFreq(int value)
    {
        getJson().getJSONObject("more_like_this").put("min_term_freq", value);
        return this;
    }

    public QueryMoreLikeThis maxQueryTerms(int value)
    {
        getJson().getJSONObject("more_like_this").put("max_query_terms", value);
        return this;
    }
}
