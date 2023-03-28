import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Shop {
    List<Item> items = new ArrayList<>();
    public void addItem(Item item){
        this.items.add(item);
    }
    public void removeItem(Item item){
        this.items.remove(item);
    }

    public List<Item> getItems() {
        return items;
    }

    public static void main(String[] args) {
        /* customerIvanov покупает товары в магазине shop
        У customerIvanov есть метод buy(Shop, Item): shop - магазин, в котором осуществляется покупка,
        item - предмет, который покупается.
        */

        Customer customerIvanov = new Customer("Ivanov",5);
        Shop shop = new Shop();
        initShop(shop);
        Bank bank = new Bank();

        /*сперва customerIvanov покупает конфету, у него достаточно на это денег
        Если вдруг денег не хватит или товара не будет в наличии будет выведено соответствующее сообщение
        (обработка ошибки путём вывода на экран сообщения об ошибке)

        если customerIvanov успешно покупает конфету, он записыет об этом в файл, передавая имя файла в
        метод writeInformationAboutShopping(String fileName, Item item). Если на позицию fileName вместо
        названия файла передать директорию, то метод выбросит IOException, которое внутри метода writeInformationAboutShopping
        оборачивается в MyIOException и пробрасывается дальше (это соответствует пункту дз оборачивание ошибки
        в новую)
        */

        Item candy = new Item("конфета",5);
        try{
            customerIvanov.buy(shop,candy);
            customerIvanov.writeInformationAboutShopping("C:\\Users\\dmitr\\java\\javarush\\archiver\\information.txt",candy);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println();

        /*Теперь customerIvanov хочет купить книгу, она значительно нужнее ему, чем конфета.
        Если книги нет в наличии, будет выведено сответсвующее сообщение.
        Если книга есть в наличии, но у customerIvanov не хватает денег, он будет несколько раз
        брать их в долг в банке и снова пытаться купить книгу, если денег всё равно не хватит
        будет выведено сообщение об ошибке (обработка исключения путём повторного использования кода)
        */

        Item book = new Item("книга",11);
        try {
            customerIvanov.buy(shop, book);
            customerIvanov.writeInformationAboutShopping("C:\\Users\\dmitr\\java\\javarush\\archiver\\information.txt",book);
        }
        catch (ItemNotFoundException | MyIOException e){
            System.out.println(e.getMessage());
        }
        catch (NotEnoughtMoneyException e){
            boolean success = false;
            for (int i = 0; i < 5; i++) {
                try{
                customerIvanov.lend(bank,5);
                customerIvanov.buy(shop,book);
                success = true;
                customerIvanov.writeInformationAboutShopping("C:\\Users\\dmitr\\java\\javarush\\archiver\\information.txt",book);
                System.out.println("вы взяли в долг и всё таки купили товар");
                break;
                }catch (MyIOException ee){
                    System.out.println(ee.getMessage());
                }
                catch (ItemNotFoundException  | NotEnoughtMoneyException ee){
                }
            }if (!success){
                System.out.println(e.getMessage());
                System.out.println("даже взяв долг вы не можете купить товар."); }
        }
        System.out.println();
        /*
        Теперь customerIvanov хочет открыть свой аккаунт и попытаться погасить имеющийся долг.
        Метод openAccount работает так: если когда-либо ранее аккаунт уже создавался
        (например, при вызове метода lend(Bank bank, int credit) аккаунт создаётся автоматически
        и на него записывается долг в размере credit), то в переменную ivanovAccount будет присвоена ссылка на него,
        если аккаунт не был ранее создан, то он будет создан при вызове метода openAccount(bank)
         */
        /*
        Затем в цикле customerIvanov несколько раз кладёт деньги на свой аккаунт, но из-за плохой
        связи этот процесс может прерваться, так как метод putMoneyToAccount(money,Account)
        выбрасывает BadConnectionException.
        Но в любом случае у переменной ivanovAccount будет вызван метод close(), который
        проверит не занулился ли счёт на аккаунте ivanovAccount. И если счёт занулился он будет
        удаён из ассоциативного массива Bank.accounts
         */
        try(Account ivanovAccount = customerIvanov.openAccount(bank)){
            System.out.println("открыли аккаунт в банке");
            for (int i = 0; i < 20; i++) {
                customerIvanov.putMoneyToAccount(1,ivanovAccount);
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println();

        //Данный метод позволяет посмотреть состояние массива Bank.accounts
        Bank.printBank();

        /*метод divideMoney(double money, int parts) класс Bank служит чтобы
        делить деньги на равные части и узнавать размер одной части, если в параметре
         parts передать 0, то в методе будет выброшено ArithmeticException, которое
         сразу же будет перехвачено и проброшено дальше (соответствует пункту дз
         пробрасывание ошибки) */
        try {
            double result1 = Bank.divideMoney(15, 4);
            double result2 = Bank.divideMoney(15,0);

            System.out.println(result1);
            System.out.println(result2);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
    public static void initShop(Shop shop){
        Item item1 = new Item("книга",11);
        Item item2 = new Item("конфета",5);
        Item item3 = new Item("настольная игра",20);
        Item item4 = new Item("велосипед",50);
        Item item5 = new Item("машина",110);
        shop.addItem(item1);
        shop.addItem(item2);
        shop.addItem(item3);
        shop.addItem(item4);
        shop.addItem(item5);
    }
}