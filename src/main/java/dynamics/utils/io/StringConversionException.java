package dynamics.utils.io;

public class StringConversionException extends IllegalArgumentException {

    private static final long serialVersionUID = -5964874757032615873L;

    public final String value;
    public final String type;

    public StringConversionException(String type, String value) {
        super (createCause(type, value));
        this.value = value;
        this.type = type;
    }

    public StringConversionException(String type, String value, Throwable cause) {
        super (createCause(type, value), cause);
        this.value = value;
        this.type = type;
    }

    private static String createCause(String type, String value) {
        return String.format("String %s couldn't be converted to type %s", value, type);
    }
}