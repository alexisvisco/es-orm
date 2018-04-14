package fr.maed.website.kwizzy.esorm.queries;

import org.json.JSONObject;

/**
 * Created by alexis on 25/10/17.
 * French author.
 */

public class MatchPhrase extends Query
{

    public MatchPhrase(String field, String value)
    {
        obj.getJSONObject("match_phrase").put(field, value);
    }

    public MatchPhrase(String field, Object value)
    {
        obj.getJSONObject("match_phrase").put(field, value);
    }

    public MatchPhrase setBoost(double value)
    {
        obj.getJSONObject("match_phrase").put("boost", value);
        return this;
    }

    @Override
    public void init()
    {
        obj.put("match_phrase", new JSONObject());
    }
}
