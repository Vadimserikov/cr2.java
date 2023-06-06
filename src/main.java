import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        lottery r = new lottery();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("""
                    Main menu:
                    1 - Добавление новой игрушки
                    2 - Изменение частоты выпадения игрушки
                    3 - Проведение лотереи и сохранение результатов
                    4 - EXIT
                    >\s""");
            var selection = sc.next();
            switch (selection) {
                case "1" -> r.addToy();
                case "2" -> r.setFrequency();
                case "3" -> r.lottery();
                case "4" -> {
                    System.out.println("До свидания!");
                    System.exit(0);
                }
                default -> System.out.println("Такой цифры нет, попробуйте еще раз.");
            }
        }
    }
}