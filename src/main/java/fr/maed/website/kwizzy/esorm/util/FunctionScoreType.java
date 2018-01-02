package fr.maed.website.kwizzy.esorm.util;

/**
 * Created by Matiyeu on 02/01/2018.
 **/


public enum FunctionScoreType
{
    SCRIPT_SCORE("script_score"),
    WEIGHT("weight"),
    RANDOM_SCORE("random_score"),
    FIELD_VALUE_FACTOR("field_value_factor"),
    GAUSS("gauss");

    private String type;

    FunctionScoreType(String type)
    {
        this.type = type;
    }

    public String getType()
    {
        return type;
    }
}
