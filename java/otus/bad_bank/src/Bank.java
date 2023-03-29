import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bank {
    private static Map<Account, Client> clientMap = createClientMap();

    public static void main(String[] args) {
        Client ivanov = new Client("Иванов", 25);
        System.out.println(getAccounts(ivanov));

        Account account5 = new Account(5);
        System.out.println(findClient(account5));
    }

    public static Client findClient(Account account) {
        Client client = clientMap.get(account);
        return client;
    }

    public static List<Account> getAccounts(Client client) {
        List<Account> result = new ArrayList<>();
        for (Map.Entry<Account, Client> pair : clientMap.entrySet()) {
            if (pair.getValue().equals(client))
                result.add(pair.getKey());
        }
        return result;
    }

    public static HashMap<Account, Client> createClientMap() {
        HashMap<Account, Client> map = new HashMap<>();

        Client ivanov = new Client("Иванов", 25);
        Client petrov = new Client("Петров", 30);
        Client sidorov = new Client("Сидоров", 35);
        Client andreev = new Client("Андреев", 40);
        Client alekseev = new Client("Алексеев", 45);
        Client matveev = new Client("Матвеев", 20);
        Client vladimirov = new Client("Владимиров", 50);
        Client bystrov = new Client("Быстров", 55);

        Account account1 = new Account();
        Account account2 = new Account();
        Account account3 = new Account();
        Account account4 = new Account();
        Account account5 = new Account();
        Account account6 = new Account();
        Account account7 = new Account();
        Account account8 = new Account();
        Account account9 = new Account();
        Account account10 = new Account();
        Account account11 = new Account();

        map.put(account1, ivanov);
        map.put(account2, ivanov);
        map.put(account3, petrov);
        map.put(account4, petrov);
        map.put(account5, petrov);
        map.put(account6, sidorov);
        map.put(account7, andreev);
        map.put(account8, alekseev);
        map.put(account9, matveev);
        map.put(account10, vladimirov);
        map.put(account11, bystrov);

        return map;
    }
}