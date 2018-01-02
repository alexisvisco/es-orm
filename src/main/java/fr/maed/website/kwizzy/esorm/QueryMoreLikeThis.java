package fr.maed.website.kwizzy.esorm;

import fr.maed.website.kwizzy.esorm.util.MultiGetFormat;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by alexis on 25/10/17.
 * French author.
 */
public class QueryMoreLikeThis extends QueryBuilder
{

    public QueryMoreLikeThis(List<ElasticDto> d, String... fields)
    {
        this(d.stream().map(e -> MultiGetFormat.fromElasticDto(e).toJson()).collect(Collectors.toSet()), fields);
    }

    public QueryMoreLikeThis(List<String> ids, String database, String collection, String... fields)
    {
        this(ids.stream().map(e -> new MultiGetFormat(database, collection, e).toJson()).collect(Collectors.toSet()), fields);

    }

    public QueryMoreLikeThis(Collection<JSONObject> datas, String... fields)
    {
        super(true);
        JSONObject m = getJson().getJSONObject("query");
        JSONObject jo = new JSONObject();
        JSONArray ja = new JSONArray();
        datas.forEach(ja::put);
        jo.put("like", ja);
        m.put("more_like_this", jo);
        if (fields.length > 0)
            m.getJSONObject("more_like_this").put("fields", Arrays.asList(fields));
    }

    public QueryMoreLikeThis minTermFreq(int value)
    {
        getJson().getJSONObject("query").getJSONObject("more_like_this").put("min_term_freq", value);
        return this;
    }

    public QueryMoreLikeThis maxQueryTerms(int value)
    {
        getJson().getJSONObject("query").getJSONObject("more_like_this").put("max_query_terms", value);
        return this;
    }

    public QueryMoreLikeThis minDocFreq(int value)
    {
        getJson().getJSONObject("query").getJSONObject("more_like_this").put("min_doc_freq", value);
        return this;
    }

    public QueryMoreLikeThis maxDocFreq(int value)
    {
        getJson().getJSONObject("query").getJSONObject("more_like_this").put("max_doc_freq", value);
        return this;
    }

    public QueryMoreLikeThis minShouldMatch(int value)
    {
        getJson().getJSONObject("query").getJSONObject("more_like_this").put("minimum_should_match", value);
        return this;
    }

    public QueryMoreLikeThis setFrom(int from)
    {
        getJson().put("from", from);
        return this;
    }

    public QueryMoreLikeThis setSize(int size)
    {
        getJson().put("size", size);
        return this;
    }

}
