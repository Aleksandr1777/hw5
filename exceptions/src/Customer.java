import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.NotSerializableException;
import java.util.Random;

public class Customer {
    private String name;
    private int budget;

    public Customer(String name, int budget) {
        this.name = name;
        this.budget = budget;
    }

    public void writeInformationAboutShopping(String fileName, Item item){
        try(FileWriter fos = new FileWriter(fileName, true)){
            fos.write("Я купил "+item.getName()+" по цене "+item.getPrice()+"\n");
        }catch (IOException e){
            throw new MyIOException("Не удалось записать информацию в файл",e);
        }
    }

    public void lend(Bank bank, int credit) {
        this.budget += credit;
        Account account = openAccount(bank);
        account.credit+=credit;
    }

    public void buy(Shop shop, Item item) throws ItemNotFoundException, NotEnoughtMoneyException {
        if (!shop.getItems().contains(item)){
            throw new ItemNotFoundException("извините у нас нет этого товара.");
        }
        if (this.budget < item.getPrice()){
            throw new NotEnoughtMoneyException("недостаточно средст.");
        }

        shop.removeItem(item);
        this.budget = this.budget - item.getPrice();
        System.out.println("товар куплен. спасибо за покупку. ");



    }

    public Account openAccount(Bank bank) {
        if (bank.accounts.containsKey(this.getName())) {
            return bank.accounts.get(this.getName());
        }
        else{
            bank.accounts.put(this.getName(),new Account(0));
            return bank.accounts.get(this.getName());
        }
    }
    public void putMoneyToAccount(int money,Account account) throws BadConnectionException{
        Random random = new Random();
        int connection = random.nextInt(7);
        if (connection==3)
            throw new BadConnectionException("связь с банком потеряна из-за плохого соединения.");
        account.credit-=money;
    }



    public String getName() {
        return name;
    }


}
