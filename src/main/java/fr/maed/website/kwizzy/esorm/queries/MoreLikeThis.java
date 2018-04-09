package fr.maed.website.kwizzy.esorm.queries;

import fr.maed.website.kwizzy.esorm.util.MultiGetFormat;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Matiyeu on 09/04/2018.
 **/


public class MoreLikeThis extends Query
{

    private JSONArray fields = new JSONArray();
    private JSONArray like = new JSONArray();

    public MoreLikeThis(List<String> datas, String database, String collection, String... fields)
    {
        super();
        getMoreLikeThis().put("fields", this.fields);
        getMoreLikeThis().put("like", this.like);
        datas.forEach(data -> this.like.put(new MultiGetFormat(database, collection, data).toJson()));
        for (String field : fields) {
            this.fields.put(field);
        }
    }


    @Override
    protected void init()
    {
        JSONObject jo = new JSONObject();
        obj.put("more_like_this", jo);
    }


    private JSONObject getMoreLikeThis()
    {
        return obj.getJSONObject("more_like_this");
    }

    public MoreLikeThis minTermFreq(int value)
    {
        getMoreLikeThis().put("min_term_freq", value);
        return this;
    }

    public MoreLikeThis maxQueryTerms(int value)
    {
        getMoreLikeThis().put("max_query_terms", value);
        return this;
    }

    public MoreLikeThis minDocFreq(int value)
    {
        getMoreLikeThis().put("min_doc_freq", value);
        return this;
    }

    public MoreLikeThis maxDocFreq(int value)
    {
        getMoreLikeThis().put("max_doc_freq", value);
        return this;
    }

    public MoreLikeThis minShouldMatch(int value)
    {
        getMoreLikeThis().put("minimum_should_match", value);
        return this;
    }

}
