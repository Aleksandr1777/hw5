import java.io.NotSerializableException;
import java.util.Objects;

public class Account {
    private static int totalId = 0;
    private int id;

    public Account(){
        totalId++;
        this.id = totalId;
    }
    public Account(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Account № "+this.id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return id == account.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
