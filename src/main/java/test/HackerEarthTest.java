package test;

import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class HackerEarthTest {

    static class Employee {
        int empId;
        String name;
        Employee() {

        }
        Employee (int id, String nam) {
            this.empId = id;
            this. name = nam;

        }

        @Override
        public String toString() {
            return "Employee{" +
                    "empId=" + empId +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) {
        int number = 5;
        String str[] ={"aaaaa", "bbbbb", "ccccc", "ddddd", "eeeee"};

        String [] newArr = Arrays.copyOfRange(str, 1, 3);
        System.out.println(Arrays.toString(newArr));
        int Q = 3;
        int[][] query = {{3,3,3},{1,5,16}, {3, 5, 9}};
        int[][] query1 = {{3,3,3},{1,5,16}, {3, 5, 8}};

        System.out.println("deep tostring= "+Arrays.deepToString(query));
        System.out.println("deep equal ="+Arrays.deepEquals(query, query1));

        Employee[] employees = new Employee[] {new Employee(1,"abc"), new Employee(2,"abc1"),
                new Employee(3,"abc3")};
        Employee[] employees1 = new Employee[] {new Employee(1,"abc"), new Employee(2,"abc1"),
                new Employee(3,"abc3")};

        System.out.println("deep Employees eq ="+Arrays.deepEquals(employees, employees1));
        System.out.println("deep Employees print ="+Arrays.deepToString(employees));

        char [] res = calCulateCharPosition(number, str, Q, query);
        System.out.println(res);

        traverseStringWithJava8();



    }

    private static void traverseStringWithJava8() {
        String str[] ={"aaaaa", "bbbbb", "ccccc", "ddddd", "eeeee"};

        List<String> list = IntStream.range(0, str.length)
                .filter(num -> num % 2 == 0)
                .mapToObj(i -> str[i]).collect(Collectors.toList());
        list.forEach(System.out::println);
    }

    private static char[] calCulateCharPosition(int number, String[] str, int q, int[][] query) {
        char[] res = new char[q];

        List<int[]> query1 = Arrays.stream(query).collect(Collectors.toList());

        query1.forEach( query2 -> {
            String ss = "";
            if (query2[0] == query2[1])
                ss = str[query2[0]];
            for (int i = query2[0]; i <= query2[1]-1 ; i++ ){
                //System.out.println("-->"+i);
                ss = ss + str[i];
            }
            //System.out.println(ss+":::ss("+query2[2]+")->"+ ss.charAt(query2[2]));

            System.out.println(ss.charAt(query2[2]));
        } );


        return res;

    }
}
