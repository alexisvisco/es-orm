package fr.maed.website.kwizzy.esorm.queries;

import org.json.JSONObject;

/**
 * Created by alexis on 25/10/17.
 * French author.
 */

public class Match extends Query
{

    public Match(String field, String value)
    {
        obj.getJSONObject("match").put(field, value);
    }

    public Match(String field, Object value)
    {
        obj.getJSONObject("match").put(field, value);
    }

    public Match setBoost(double value)
    {
        obj.getJSONObject("match").put("boost", value);
        return this;
    }

    @Override
    void init()
    {
        obj.put("match", new JSONObject());
    }
}
