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
		case null -> "nothing to format.";
		case Integer i -> String.format("int %d", i);
		case Long l -> String.format("long %d", l);
		case Double d -> String.format("double %f", d);
		case String s -> String.format("String %s", s);
		default -> obj.getClass().getName();
		};
	}

	@Test
	public void testPersonFormattingWithGuards() {
		displayPerson(new Person("Ariella Mermaid", Gender.NON_BINARY, "Deep Blue Ocean"));
		displayPerson(new Person("Alicia Keys", Gender.FEMALE, "New York"));
		displayPerson(new Person("Frank Sinatra", Gender.MALE, "New York"));
	}

	record Person(String name, Gender gender, String address) {
	} // since Java 14: immutable Records, reducing a lot of boilerplate code

	enum Gender {
		MALE, FEMALE, NON_BINARY
	}

	static void displayPerson(Person p) {
		switch (p) {
		case Person(var name, var gender, var a) when Gender.MALE.equals(gender) 
			-> _display(p, "(he/him)"); 
		// would have liked unnamed parameter _ for address (instead of var a), but IDE does not support this yet
		case Person(var name, var gender, var a) when Gender.FEMALE.equals(gender) 
			-> _display(p, "(she/her)");
		default 
			-> _display(p, "(they/them)");
		}
	}

	private static void _display(Person p, String pronouns) {
		out.println(p.name() + "  " + pronouns);
	}

}
