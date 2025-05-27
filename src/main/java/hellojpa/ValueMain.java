package hellojpa;

public class ValueMain {

    public static void main(String[] args) {

        int a = 10;
        int b = 10;

        System.out.println("a == b : " + (a == b));

        Address address1 = new Address("city", "street", "zipcode");
        Address address2 = new Address("city", "street", "zipcode");

        System.out.println("address1 == address2 : " + (address1 == address2)); // 동일성 비교 : false 참조값 다름
        System.out.println("address1 equals address2 : " + (address1.equals(address2))); // 동등성 비교 : false equals의 기본이 == 비교 -> 재정의
    }
}
