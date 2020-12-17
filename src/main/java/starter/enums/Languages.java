package starter.enums;

import java.util.HashMap;
import java.util.Map;

public class Languages {
    public static final String RUSSIAN = "Russian";
    private static final Map<String, String> codes = new HashMap<>();

    static {
        codes.put(RUSSIAN, "ru");
    }

    private Languages() {}

    public static String getCodeByLanguage(String language) {
        return codes.get(language);
    }

}
