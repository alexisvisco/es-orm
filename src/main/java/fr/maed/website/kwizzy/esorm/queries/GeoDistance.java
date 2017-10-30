package fr.maed.website.kwizzy.esorm.queries;

import org.json.JSONObject;

/**
 * Created by alexis on 25/10/17.
 * French author.
 */
public class GeoDistance extends Query
{

    public GeoDistance(String fieldLocation, int distance, double lat, double lon)
    {
        obj.getJSONObject("geo_distance")
                .put("distance", distance + "km")
                .put(fieldLocation, new JSONObject().put("lat", lat).put("lon", lon));
    }

    @Override
    void init()
    {
        obj.put("geo_distance", new JSONObject());
    }
}
