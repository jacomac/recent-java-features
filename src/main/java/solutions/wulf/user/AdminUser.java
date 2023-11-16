package solutions.wulf.user;

public class AdminUser extends User {
    @Override
    protected String getSubType() {
        return this.getClass().getName();
    }

    @Override
    protected String getName() {
        return "Admin Adjani";
    }

    public String doAdmin() {
        var sb = new StringBuilder();
        sb.append("doAdmin: ");
        sb.append(toString());
        return sb.toString();
    }
}
