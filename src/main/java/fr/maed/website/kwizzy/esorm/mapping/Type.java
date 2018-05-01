package fr.maed.website.kwizzy.esorm.mapping;

public enum Type {
        TEXT("text"),
        KEYWORD("keyword"),
        LONG("long"),
        INT("integer"),
        SHORT("short"),
        BYTE("byte"),
        DOUBLE("double"),
        FLOAT("float"),
        HALF_FLOAT("half_float"),
        SCALED_FLOAT("scaled_float"),
        DATE("date"),
        BOOL("boolean"),
        BINARY("binary"),
        INT_RANGE("integer_range"),
        FLOAT_RANGE("float_range"),
        LONG_RANGE("long_range"),
        DOUBLE_RANGE("double_range"),
        DATE_RANGE("date_range"),
        OBJECT("object"),
        NESTED("nested"),
        GEO_POINT("geo_point"),
        GEO_SHAPE("geo_shape"),
        IP("ip"),
        COMPLETION("completion"),
        TOKEN_COUNT("token_count"),
        MURMUR3("murmur3")
        ;

        public String value;

        Type(String value) {
            this.value = value;
        }
    }