import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


public class lottery {
    private static ArrayList<toy> toys = new ArrayList<>();
    private static PriorityQueue<toy> prizes = new PriorityQueue<>();

    private static int idCounter = 0;

    public void addToy() {
        Scanner scan = new Scanner(System.in);
        String title;
        int frequency;
        while (true) {
            System.out.print("Введите игрушку: ");
            title = scan.nextLine();
            if (title.isEmpty()) {
                System.out.println("Некорректный ввод, попробуйте еще раз.");
                break;
            }
            System.out.print("Введите частоту выпадения игрушки: ");
            String value = scan.nextLine();
            if (isDigit(value)) {
                frequency = Integer.parseInt(value);
                if (frequency <= 0) {
                    System.out.println("Некорректный ввод, попробуйте еще раз.");
                } else {
                    toy toy = new toy(idCounter, title, frequency);
                    if (!toys.contains(toy) || toys.size() == 0) {
                        idCounter++;
                        toys.add(toy);
                        System.out.println("Игрушка была добавлена.");
                    } else {
                        System.out.println("Такая игрушка уже есть.");
                    }
                }
            } else {
                System.out.println("Некорректный ввод, попробуйте еще раз.");
            }
            break;
        }
    }

    public void setFrequency() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Введите id игрушки: ");
        String value = scan.nextLine();
        if (isDigit(value)) {
            int selectedId = Integer.parseInt(value);
            if (selectedId >= 0 && selectedId < toys.size()) {
                System.out.println("игрушка " + toys.get(selectedId).getToyTitle() +
                        " имеет частоту выпадения " + toys.get(selectedId).getToyVictoryFrequency());
                System.out.print("Введите частоту выпадения: ");
                value = scan.nextLine();
                if (isDigit(value)) {
                    int newFrequency = Integer.parseInt(value);
                    toys.get(selectedId).setToyVictoryFrequency(newFrequency);
                    System.out.println("Частота выпадения была изменена.");
                } else {
                    System.out.println("Некорректный ввод, попробуйте еще раз.");
                }
            } else {
                System.out.println("Не существует игрушки с таким id.");
            }
        } else {
            System.out.println("Некорректный ввод, попробуйте еще ра.");
        }
    }

    private static boolean isDigit(String s) throws NumberFormatException {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public toy getPrize() {
        if (prizes.size() == 0) {
            Random rnd = new Random();
            for (toy toy : toys) {
                for (int i = 0; i < toy.getToyVictoryFrequency(); i++) {
                    toy temp = new toy(toy.getToyId(), toy.getToyTitle(), rnd.nextInt(1, 10));
                    prizes.add(temp);
                }
            }
        }
        return prizes.poll();
    }

    public void lottery() {
        if (toys.size() >= 2) {
            toy prize = getPrize();
            System.out.println("Приз: " + prize.getToyTitle());
            saveResult(prize.getInfo());
        } else {
            System.out.println("Вы доожны добавить хотя бы две игрушки.");
        }
    }

    private void saveResult(String text) {
        File file = new File("Result.txt");
        try {
            file.createNewFile();
        } catch (Exception ignored) {
            throw new RuntimeException();
        }
        try (FileWriter fw = new FileWriter("Result.txt", true)) {
            fw.write(text + "\n");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}

