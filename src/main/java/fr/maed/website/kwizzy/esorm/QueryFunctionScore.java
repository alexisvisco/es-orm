package fr.maed.website.kwizzy.esorm;

import fr.maed.website.kwizzy.esorm.queries.Bool;
import fr.maed.website.kwizzy.esorm.util.FunctionScoreType;
import org.json.JSONObject;

/**
 * Created by Matiyeu on 02/01/2018.
 **/


public class QueryFunctionScore extends QueryBuilder
{

//    {
//        "query": {
//        "function_score" : {
//            "query" : {
//                "bool" : {
//                    "must" : {
//                        "term" : { "adOnFront.onFront" : true}
//                    }
//                }
//
//            },
//            "random_score" : {}
//        }
//    }
//    }

    JSONObject scriptScore = new JSONObject();
    JSONObject weight = new JSONObject();
    JSONObject randomScore = new JSONObject();
    JSONObject fieldValueFactor = new JSONObject();
    JSONObject gauss = new JSONObject();

    public QueryFunctionScore(FunctionScoreType functionScoreType)
    {
        super(true);
        JSONObject m = getJson().getJSONObject("query");
        JSONObject jo = new JSONObject();
        jo.put("query", new JSONObject().put("bool", mainBool.getBool()));

        switch (functionScoreType) {
            case SCRIPT_SCORE:
                jo.put(functionScoreType.getType(), scriptScore);
            case WEIGHT:
                jo.put(functionScoreType.getType(), weight);
            case RANDOM_SCORE:
                jo.put(functionScoreType.getType(), randomScore);
            case FIELD_VALUE_FACTOR:
                jo.put(functionScoreType.getType(), fieldValueFactor);
            case GAUSS:
                jo.put(functionScoreType.getType(), gauss);
        }

        m.put("function_score", jo);

    }

    public QueryFunctionScore setSeed(long seed)
    {
        randomScore.put("seed", seed);
        return this;
    }

    public QueryFunctionScore setField(String field)
    {
        randomScore.put("field", field);
        return this;
    }
}
