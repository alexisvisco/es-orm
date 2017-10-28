package fr.maed.website.kwizzy.esorm;

import fr.maed.website.kwizzy.esorm.queries.Query;
import fr.maed.website.kwizzy.esorm.queries.Sort;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by alexis on 25/10/17.
 * French author.
 */
public class QueryBuilder
{
    private JSONObject json = new JSONObject();

    private JSONArray must = new JSONArray();
    private JSONArray should = new JSONArray();
    private JSONArray filter = new JSONArray();
    private JSONArray mustNot = new JSONArray();

    public QueryBuilder()
    {
        JSONObject bool = json
                .put("query", new JSONObject())
                .getJSONObject("query")
                .put("bool", new JSONObject())
                .getJSONObject("bool");
        bool.put("must", must);
        bool.put("should", should);
        bool.put("filter", filter);
        bool.put("must_not", mustNot);
    }

    public QueryBuilder setPaginationSetting(int from, int size)
    {
        json.put("from", from);
        json.put("size", size);
        return this;
    }

    public QueryBuilder addMust(Query q)
    {
        must.put(q.toJson());
        return this;
    }

    public QueryBuilder addShould(Query q)
    {
        should.put(q.toJson());
        return this;
    }

    public QueryBuilder addFilter(Query q)
    {
        filter.put(q.toJson());
        return this;
    }

    public QueryBuilder addMustNot(Query q)
    {
        mustNot.put(q.toJson());
        return this;
    }

    public QueryBuilder addSort(Sort s)
    {
        json.put("sort", s.getArr());
        return this;
    }

    public JSONObject getJson()
    {
        return json;
    }

    public QueryBuilder setJson(JSONObject json)
    {
        this.json = json;
        return this;
    }
}
