import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> names = new ArrayList<>();
        names.add("Ivan");
        names.add("Valera");
        names.add("Jane");
        names.add("Lena");
        System.out.println(task2(names));
        task3(new String[]{"1, 2, 0", "4, 5"});
        long a = 25214903917L;
        long c = 11L;
        long m = (long) Math.pow(2, 48);
        Stream<Long> randomNumbers = task4(0L, a, c, m);
        randomNumbers.limit(10).forEach(System.out::println);
    }

    public static String task1(ArrayList<String> names){
        //за допомогою стріма вибираємо непарні елементи
        ArrayList<String> namesList = IntStream
                .range(0, names.size())
                .filter(i -> i % 2 != 0)
                .mapToObj(i -> names.get(i))
                .collect(Collectors.toCollection(ArrayList::new));
        //за допомогою стрінг баферу створюємо строку "індекс. ім'я "
        StringBuffer buffer = new StringBuffer();
        int index = 1;
        for(String name: namesList){
            buffer.append(index);
            buffer.append(". ");
            buffer.append(name);
            buffer.append(", ");
            index += 2;
        }
        return buffer.toString();
    }

    public static ArrayList<String> task2(ArrayList<String> names){
        ArrayList<String> namesList = names
                .stream()
                .map(String::toUpperCase)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toCollection(ArrayList::new));
        return namesList;
    }

    public static void task3(String[] nums){
        String result = Arrays
                .stream(nums)
                .flatMap(str -> Arrays.stream(str.split(", ")))
                .map(Integer::valueOf)
                .sorted()
                .map(Object::toString)
                .collect(Collectors.joining(", "));
        System.out.println(result);

    }

    //цей метод повертає стрім, який ітерує за вказаною формулою
    public static Stream<Long> task4(long seed, long a, long c, long m){
        return Stream.iterate(seed, n -> (a * n + c) % m);
    }

    public static <T> Stream<T> zip(Stream<T> first, Stream<T> second) {
        //створюємо ітератори для кожного стріму
        Iterator<T> iterator1 = first.iterator();
        Iterator<T> iterator2 = second.iterator();

        Stream.Builder<T> builder = Stream.builder();
        //поки один з стрімів не закінчиться перемішуємо їх
        while (iterator1.hasNext() && iterator2.hasNext()) {
            builder.accept(iterator1.next());
            builder.accept(iterator2.next());
        }

        return builder.build();
    }



}