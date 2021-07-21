package test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Java8 {

    static class Dish {
        int dishId;
        String name;

        Dish(int id , String name) {
            this.dishId = id;
            this.name = name;
        }
        public int getDishId() {
            return dishId;
        }

        public void setDishId(int dishId) {
            this.dishId = dishId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Dish{" +
                    "dishId=" + dishId +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
    public static void main(String[] args) {
        String[] arr = {"abcd", "pqrs", "lmno", "xyz"};
        String joiningStr = Arrays.stream(arr).collect(Collectors.joining(","));
        System.out.println("Collectors.joining="+joiningStr);

        Dish[] dishes = {new Dish(2, "testy"), new Dish(2, "spicy"),
                        new Dish(3, "Aww"), new Dish(3, "Dessart")};
        IntSummaryStatistics statistics = Arrays.stream(dishes).collect(Collectors.summarizingInt(Dish::getDishId));
        System.out.println("IntSummaryStatistics"+statistics);

        int totalCalories = Arrays.stream(dishes).collect(Collectors.reducing(
                0, Dish::getDishId, (i, j) -> i + j));

        int totalCalories1 = Arrays.stream(dishes).collect(Collectors.reducing(
                0, Dish::getDishId, Integer::sum));

        int totalCalories2 = Arrays.stream(dishes).map(Dish::getDishId).reduce(Integer::sum).get();
        int totalCalories3 = Arrays.stream(dishes).mapToInt(Dish::getDishId).sum();
        System.out.println("totalCalories, Collectors.reducing()="+totalCalories);
        System.out.println("totalCalories1, Collectors.reducing(, , Integer::sum)="+totalCalories1);
        System.out.println("totalCalories2="+totalCalories2);

        Comparator<Dish> dishCompare = Comparator.comparing(Dish::getName)
                                                .thenComparing(Dish::getDishId);
        List<Dish> sortedList = Arrays.asList(dishes).stream().sorted(dishCompare).collect(Collectors.toList());
        System.out.println("Comparator.comparing().thenComparing()="+sortedList);
        //Active employees with active accounts
        /** result = employeeList.stream()
                .filter(isEmployeeActive.and(isAccountActive))
                .map(e -> e.getId().toString())
                .collect(Collectors.joining(",", "[", "]"));

         //Active employees with inactive accounts
         result = employeeList.stream()
         .filter(isEmployeeActive.and(isAccountActive.negate()))
         .map(e -> e.getId().toString())
         .collect(Collectors.joining(",", "[", "]"));
         */

        /** to remove duplicates */
        ArrayList<Integer> numbersList
                = new ArrayList<>(Arrays.asList(1, 1, 2, 3, 3, 3, 4, 5, 6, 6, 6, 7, 8));
        System.out.println("numbersList="+numbersList);
        //way 1:-
        List<Integer> listWithoutDuplicates = numbersList.stream()
                .distinct()
                .collect(Collectors.toList());
        System.out.println("distinct="+listWithoutDuplicates);

        //way 2
        Set<Integer> setWithoutDuplicates = numbersList.stream()
                .collect(Collectors.toSet());
        System.out.println("setWithoutDuplicates="+setWithoutDuplicates);

        /** Collectors.toMap() to count occurances */
        Map<Integer, Long> elementCountMap = numbersList.stream()
                .collect(Collectors.toMap(Function.identity(),  v->1L, Long::sum));
        System.out.println("Collectors.toMap="+elementCountMap);

        System.out.println("File reading using Java 8......");
        Path filePath = Paths.get("/home/priyom", "testFile.txt");
        //try-with-resources
        try (Stream<String> lines = Files.lines( filePath ))
        {
            //lines.forEach(System.out::println);

            // filter string from stream of lines.
            List<String> filteredLines = lines
                    .filter(s -> s.contains("CTC"))
                    .collect(Collectors.toList());
            filteredLines.forEach(System.out::println);

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        System.out.println("Writing into File using Java 8 - way1 Files.newBufferedWriter(path)......");
        //Get the file reference
        Path path = Paths.get("/home/priyom/testOutput.txt");
        //Use try-with-resource to get auto-closeable writer instance
        try (BufferedWriter writer = Files.newBufferedWriter(path))
        {
            writer.write("Hello World !!");
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Writing into File using Java 8 - way2 Files.write(......");
        String content = "Hello World !!";
        try {
            Files.write(Paths.get("/home/priyom/testOutput2.txt"), content.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Listing files from directory");
        try {
            List<File> files = Files.list(Paths.get("/home/priyom/"))
                    //.filter(Files::isRegularFile)  ---> to print only files not sub folder
                    //.filter(path -> path.toFile().isHidden())
                    .map(Path::toFile)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
