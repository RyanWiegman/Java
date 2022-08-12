import java.util.Scanner;

class general_topic {
    public static void variables() {
        int x = 123;
        double dec = 1.2455;
        boolean flag = true;
        char symbol = '@';
        String my_name = "ryan";

        System.out.println("Hello, World!");
        System.out.println("x equals: " + x);
        System.out.println("decimal equals: " + dec);
        System.out.println(flag);
        System.out.println(symbol);
        System.out.println("my name is: " + my_name);
    }

    public static void string() {
        String one = "water";
        String two = "kool-aid";
        String temp = null;

        temp = one;
        one = two;
        two = temp;

        System.out.println("one: " + one);
        System.out.println("two: " + two);
    }

    public static void user_input() {
        
        Scanner scan = new Scanner(System.in);

        System.out.println("What is you name? ");
        String name = scan.nextLine();
        System.out.println("How old are you? ");
        int age = scan.nextInt();

        System.out.println("hello: " + name);
        System.out.println("You are " + age + " years old.");

        scan.close();
    }

    public static void expressions() {
        int friends = 10;
        friends = friends - 1;
        friends = friends * 2;
        friends = friends % 2;
        friends++;

        System.out.println("Friends: " + friends);
    }
    public static void main(String[] args) {
        variables();
        string();
        user_input();
        expressions();
    }
}