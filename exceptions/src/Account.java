import java.util.Objects;

public class Account implements AutoCloseable {
    private static int countAccounts = 0 ;
    public int id;
    public int credit;
    public Account(int credit){
        countAccounts++;
        this.id = countAccounts;
        this.credit = credit;
    }

    @Override
    public void close() throws Exception {
        if (this.credit<=0)
            Bank.removeAccount(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return id == account.id && credit == account.credit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, credit);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", credit=" + credit +
                '}';
    }
}
