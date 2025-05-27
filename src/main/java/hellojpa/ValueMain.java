package hellojpa;

public class ValueMain {

    public static void main(String[] args) {

//        Integer c = new Integer(10);
//        Integer d = c;
//        d.setValue(20) // c, d => 20

        int a = 10;
        int b = a;

        a = 20;

        System.out.println("a = " + a); // 10
        System.out.println("b = " + b); // 20
    }
}
