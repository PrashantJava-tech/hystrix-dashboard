package test;

public class SingeltoneDemo {

    private static SingeltoneDemo singeltoneDemo;
    String s ;
    private SingeltoneDemo(String s){
        this.s = s;
    }

    @Override
    public String toString() {
        return "SingeltoneDemo{" +
                "s='" + s + '\'' +
                '}';
    }

    public static SingeltoneDemo getSingeltoneObj(){
        if(singeltoneDemo == null){
            singeltoneDemo = new SingeltoneDemo("Hi");
        }
        return singeltoneDemo;
    }
}
