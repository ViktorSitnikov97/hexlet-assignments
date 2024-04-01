package exercise;

import java.lang.reflect.Field;
// BEGIN
import java.util.ArrayList;
import java.util.List;

public final class Validator {
    public static List<String> validate(Object object) {
        List<String> list = new ArrayList<>();
        try {
            Field[] fields = object.getClass().getDeclaredFields();

            for (Field field : fields) {
                field.setAccessible(true);
                Object value = field.get(object);
                if (field.isAnnotationPresent(NotNull.class) && value == null) {
                    list.add(field.getName());
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return list;

    }

}
// END
