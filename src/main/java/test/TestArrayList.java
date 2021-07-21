package test;


import java.util.ArrayList;


public class TestArrayList extends Exception{

    String s ;
    TestArrayList(String s){
        super(s);
    }

    public String getS() {
        return s;
    }

    public static void main(String[] args) {

        ArrayList<String> arr1 = new ArrayList<String>();
        arr1.add("abc");
        arr1.add("lmn");
        arr1.add("pqr");

        ArrayList<String> arr2 = new ArrayList<String>();
        arr2.add("xyz");
        arr2.add("kmn");
        arr2.add("lmn");

        ArrayList<String> arr3 = new ArrayList<String>();
        arr3.add("abc");
        arr3.add("xyz");
        arr3.add("kmn");
        arr3.add("lmn");
        arr3.add("pqr");

        System.out.println(arr1);
        System.out.println(arr2);
        System.out.println(arr3);

/*        arr1.removeIf(s -> s.startsWith("l"));
        System.out.println(arr1);

        arr1.retainAll(arr2);
        System.out.println(arr1);*/

        arr1.removeAll(arr2);
        System.out.println(arr1);

        arr3.removeAll(arr1);
        System.out.println(arr3);

        try{
            int x = 10/5;
            throw new TestArrayList("Exception");
        } catch (TestArrayList ex) {
            System.out.println(ex);
        }

        //Call singeltone
        SingeltoneDemo singeltoneDemo = SingeltoneDemo.getSingeltoneObj();
        System.out.println(singeltoneDemo.toString());
        SingeltoneDemo singeltoneDemo1 = SingeltoneDemo.getSingeltoneObj();
        System.out.println(singeltoneDemo1.toString());
        SingeltoneDemo singeltoneDemo2= SingeltoneDemo.getSingeltoneObj();
        System.out.println(singeltoneDemo2.toString());

        System.out.println(singeltoneDemo2 == singeltoneDemo1);
    }
}
