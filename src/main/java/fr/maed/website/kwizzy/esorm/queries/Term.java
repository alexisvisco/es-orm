package fr.maed.website.kwizzy.esorm.queries;

import org.json.JSONObject;

/**
 * Created by alexis on 25/10/17.
 * French author.
 */

public class Term extends Query
{

    public Term(String field, String value)
    {
        obj.getJSONObject("term").put(field, value);
    }

    public Term(String field, Object value)
    {
        obj.getJSONObject("term").put(field, value);
    }

    public Term setBoost(double value)
    {
        obj.getJSONObject("term").put("boost", value);
        return this;
    }

    @Override
    public void init()
    {
        obj.put("term", new JSONObject());
    }
}
