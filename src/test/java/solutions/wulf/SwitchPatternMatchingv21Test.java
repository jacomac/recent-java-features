package solutions.wulf;

import org.junit.jupiter.api.Test;
import solutions.wulf.user.AdminUser;

import static java.lang.System.out;

public class SwitchPatternMatchingv21Test {

    @Test
    public void testMultiTypeFormatting() {
        format(null);
        format(32);
        format(72985723L);
        format(30.2d);
        format("No Strings attached?");
        format(new AdminUser()); // should trigger default
    }

    static String format(Object obj) {
        return switch (obj) {
            case null      -> out.println("nothing to format.");
            case Integer i -> String.format("int %d", i);
            case Long l    -> String.format("long %d", l);
            case Double d  -> String.format("double %f", d);
            case String s  -> String.format("String %s", s);
            default        -> obj.getClass().getName();
        };
    }
}
