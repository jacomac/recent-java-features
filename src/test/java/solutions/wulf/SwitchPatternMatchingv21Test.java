package solutions.wulf;

import static java.lang.System.out;

import java.util.List;

import org.junit.jupiter.api.Test;

import solutions.wulf.user.AdminUser;

public class SwitchPatternMatchingv21Test {

    @Test
    public void testMultiTypeFormatting() {
    	List<Object> variousTypes = List.of(32, 72985723L, 30.2d, "No Strings attached?", new AdminUser());
    	variousTypes.forEach(x -> out.println(format(x)));
    	out.println(format(null));
    }

    static String format(Object obj) {
        return switch (obj) {
            case null      -> "nothing to format.";
            case Integer i -> String.format("int %d", i);
            case Long l    -> String.format("long %d", l);
            case Double d  -> String.format("double %f", d);
            case String s  -> String.format("String %s", s);
            default        -> obj.getClass().getName();
        };
    }
}
