package solutions.wulf.user;

public class RegularUser extends User {
    @Override
    protected String getSubType() {
        return this.getClass().getName();
    }

    @Override
    protected String getName() {
        return "Freddy RegularGuy";
    }

    public String doRegular() {
        var sb = new StringBuilder();
        sb.append("doRegular: ");
        sb.append(toString());
        return sb.toString();
    }
}
