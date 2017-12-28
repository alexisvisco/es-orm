package fr.maed.website.kwizzy.esorm.queries;

import org.json.JSONObject;

/**
 * Created by alexis on 25/10/17.
 * French author.
 */

public class Prefix extends Query
{

    public Prefix(String field, String value)
    {
        obj.getJSONObject("match_phrase_prefix").put(field, value);
    }

    public Prefix setBoost(double value)
    {
        obj.getJSONObject("match_phrase_prefix").put("boost", value);
        return this;
    }

    @Override
    public void init()
    {
        obj.put("match_phrase_prefix", new JSONObject());
    }
}
