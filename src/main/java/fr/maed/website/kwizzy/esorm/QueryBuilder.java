package fr.maed.website.kwizzy.esorm;

import fr.maed.website.kwizzy.esorm.queries.Bool;
import fr.maed.website.kwizzy.esorm.queries.Query;
import fr.maed.website.kwizzy.esorm.queries.Sort;
import org.json.JSONObject;

/**
 * Created by alexis on 25/10/17.
 * French author.
 */
public class QueryBuilder
{
    private JSONObject json = new JSONObject();
    private Bool mainBool;

    public QueryBuilder()
    {
        this.mainBool = new Bool();
        json.put("query", new JSONObject().put("bool", mainBool.getBool()));

    }

    protected QueryBuilder(boolean b)
    {
        json.put("query", new JSONObject());
    }

    public QueryBuilder setPaginationSetting(int from, int size)
    {
        json.put("from", from);
        json.put("size", size);
        return this;
    }

    public QueryBuilder addMust(Query q)
    {
        mainBool.addMust(q);
        return this;
    }

    public QueryBuilder addShould(Query q)
    {
        mainBool.addShould(q);
        return this;
    }

    public QueryBuilder addFilter(Query q)
    {
        mainBool.addFilter(q);
        return this;
    }

    public QueryBuilder addMustNot(Query q)
    {
        mainBool.addMustNot(q);
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

    public QueryBuilder setFrom(int from)
    {
        getJson().put("from", from);
        return this;
    }

    public QueryBuilder setSize(int size)
    {
        getJson().put("size", size);
        return this;
    }
}
