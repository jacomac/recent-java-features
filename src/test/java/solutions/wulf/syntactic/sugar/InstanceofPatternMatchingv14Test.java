package solutions.wulf.syntactic.sugar;

import org.junit.jupiter.api.Test;
import solutions.wulf.user.AdminUser;
import solutions.wulf.user.RegularUser;
import solutions.wulf.user.User;

import static org.junit.jupiter.api.Assertions.fail;
import static java.lang.System.out;

public class InstanceofPatternMatchingv14Test {

    @Test
    public void testInstanceOfPatterMatching()
    {
        var regularUser = new RegularUser();
        var adminUser = new AdminUser();
        doPatterMatching(adminUser);
        doPatterMatching(regularUser);
    }

    private void doPatterMatching(User user) {
        if (user instanceof AdminUser a) {
            out.println(a.doAdmin());
        } else if (user instanceof RegularUser r) {
            out.println(r.doRegular());
        } else {
            fail("this shouldn't happen");
        }
    }
}
