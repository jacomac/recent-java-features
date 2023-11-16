package solutions.wulf.user;

public abstract class User {
    @Override
    public String toString() {
        return String.format("This User is named %s and is of sub type %s", getName(), getSubType());
    }

    protected abstract String getSubType();

    protected abstract String getName();
}
