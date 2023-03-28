import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bank {
    public static Map<String, Account> accounts = new HashMap<>();

    public static void removeAccount(Account account) {
        for (Map.Entry<String, Account> pair : accounts.entrySet()) {
            if (pair.getValue().equals(account))
                accounts.remove(pair.getKey());
        }
    }

    public static void printBank() {
        if (Bank.accounts.isEmpty())
            System.out.println("на данный момент в банке нет ни одного аккаунта");
        for (Map.Entry<String, Account> pair : accounts.entrySet()) {
            System.out.println(pair.getKey() + pair.getValue());
        }
    }

    public static double divideMoney(int money, int parts){
        double result;
        try{
            result = money/parts;
        } catch (ArithmeticException e){
            throw e;
        }
        return result;
    }


}