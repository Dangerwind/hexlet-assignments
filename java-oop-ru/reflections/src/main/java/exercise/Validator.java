package exercise;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
// BEGIN
class Validator {
    public static List<String> validate(Address myAddres) {
        var newList = new ArrayList<String>();
        Class<?> addrObj = myAddres.getClass();
        Field[] fields = addrObj.getDeclaredFields();
        for (Field field : fields) {
            String fieldName = field.toString().split("\\.") [fields.length - 1];
            boolean isNotNull = field.isAnnotationPresent(NotNull.class);
            field.setAccessible(true);
            try {
                String value = (String) field.get(myAddres);
                if ((isNotNull) && (value == null)) {
                    newList.add(fieldName);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return newList;
    }

    public static Map<String, List<String>> advancedValidate(Address myAddres) {
        var newMap = new HashMap<String, List<String>>();

        Class<?> addrObj = myAddres.getClass();
        Field[] fields = addrObj.getDeclaredFields();

        for (Field field : fields) {
            String fieldName = field.toString().split("\\.")[fields.length - 1];
            boolean isNotNull = field.isAnnotationPresent(NotNull.class);
            field.setAccessible(true);
            try {
                var errList = new ArrayList<String>();
                String value = (String) field.get(myAddres);
                if (field.isAnnotationPresent(MinLength.class)) {
                    var minL = field.getAnnotation(MinLength.class).minLength();
                    if (value.length() < minL) {
                        errList.add("length less than " + minL);
                    }
                }
                if ((isNotNull) && (value == null)) {
                    errList.add("can not be null");
                }
                if (!errList.isEmpty()) {
                    newMap.put(fieldName, errList);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
       // System.out.println(" ---- " + newMap.toString());
        return newMap;
    }
}

// END
