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
    }

    public Bool addMust(Query q)
    {
        if (must == null)
            must = obj.getJSONObject("bool").put("must", new JSONArray()).getJSONArray("must");
        must.put(q.toJson());
        return this;
    }

    public Bool addShould(Query q)
    {
        if (should == null)
            should = obj.getJSONObject("bool").put("should", new JSONArray()).getJSONArray("should");
        should.put(q.toJson());
        return this;
    }

    public Bool addFilter(Query q)
    {
        if (filter == null)
            filter = obj.getJSONObject("bool").put("filter", new JSONArray()).getJSONArray("filter");
        filter.put(q.toJson());
        return this;
    }

    public Bool addMustNot(Query q)
    {
        if (mustNot == null)
            mustNot = obj.getJSONObject("bool").put("must_not", new JSONArray()).getJSONArray("must_not");
        mustNot.put(q.toJson());
        return this;
    }

    public JSONObject getBool()
    {
        return obj.getJSONObject("bool");
    }
}
