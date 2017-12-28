package fr.maed.website.kwizzy.esorm.queries;

import org.json.JSONArray;
import org.json.JSONObject;

public class Bool extends Query
{

    private JSONArray must;
    private JSONArray mustNot;
    private JSONArray should;
    private JSONArray filter;

    @Override
    public void init() {
        this.obj = new JSONObject().put("bool", new JSONObject());
        must = obj.put("must", new JSONArray()).getJSONArray("must");
        filter = obj.put("filter", new JSONArray()).getJSONArray("filter");
        should = obj.put("should", new JSONArray()).getJSONArray("should");
        mustNot = obj.put("must_not", new JSONArray()).getJSONArray("must_not");
    }

    public Bool addMust(Query q)
    {
        must.put(q.toJson());
        return this;
    }

    public Bool addShould(Query q)
    {
        should.put(q.toJson());
        return this;
    }

    public Bool addFilter(Query q)
    {
        filter.put(q.toJson());
        return this;
    }

    public Bool addMustNot(Query q)
    {
        mustNot.put(q.toJson());
        return this;
    }
}
