package fr.maed.website.kwizzy.esorm.mapping;

public enum TermVector {
    NO("no"),
    YES("yes"),
    WITH_POSITIONS("with_positions"),
    WITH_OFFSETS("with_offsets"),
    WITH_POSITIONS_OFFSETS("with_positions_offsets")
    ;
    String value;

    TermVector(String value) {
        this.value = value;
    }
}
