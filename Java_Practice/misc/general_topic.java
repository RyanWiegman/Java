import java.util.Scanner;
import java.util.Random;
import javax.swing.JOptionPane;

class general_topic {
    public static void variables() {
        int x = 123;
        double dec = 1.2455;
        boolean flag = true;
        char symbol = '@';
        String myName = "ryan";

        System.out.println("Hello, World!");
        System.out.println("x equals: " + x);
        System.out.println("decimal equals: " + dec);
        System.out.println(flag);
        System.out.println(symbol);
        System.out.println("my name is: " + myName);
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

    public static void gui() {
        String name = JOptionPane.showInputDialog("Enter your name ");
        JOptionPane.showMessageDialog(null, "Hello " + name);

        int age = Integer.parseInt(JOptionPane.showInputDialog("Enter your age."));
        JOptionPane.showMessageDialog(null, "You are " + age + " years old.");
    }

    public static void math() {
        double x = 3.14;
        double y = -10;

        double z = Math.max(x, y);
        double zz = Math.abs(y);
        double zzz = Math.sqrt(x);
        double zzzz = Math.round(x);
        double zx = Math.ceil(x); // ALWAYS WILL ROUND UP.
        double zxx = Math.floor(x); // ALWAYS WILL ROUND DOWN.

        System.out.println("max: " + z);
        System.out.println("abs: " + zz);
        System.out.println("sqrt: " + zzz);
        System.out.println("round: " + zzzz);
        System.out.println("ceil: " + zx);
        System.out.println("floor: " + zxx);
    }

    public static void findHypotenuse() {
        double x;
        double y;
        double z;

        Scanner scan = new Scanner(System.in);

        System.out.println("Enter side x: ");
        x = scan.nextDouble();
        System.out.println("Enter side y: ");
        y = scan.nextDouble();

        z = Math.sqrt((x*x) + (y*y));

        System.out.println("The hypotenuse is: " + z);

        scan.close();
    }

    public static void randomNumbers() {
        Random random = new Random();

        int x = random.nextInt(6) + 1;

        System.out.println("Random number: " + x);
    }

    public static void switchs() {
        String day = "pizza";

        switch(day) {
            case "Sunday" : System.out.println("It is Sunday!");
            break;
            case "Monday" : System.out.println("It is Monday!");
            break;
            case "Tuesday" : System.out.println("It is Tuesday!");
            break;
            case "Wednesday" : System.out.println("It is Wednesday!");
            break;
            case "Thursday" : System.out.println("It is Thursday!");
            break;
            case "Friday" : System.out.println("It is Friday!");
            break;
            case "Saturday" : System.out.println("It is Saturday!");
            break;
            default: System.out.println("That is not a day!");

        }
    }

    public static void operators() {
        int temp = 25;

        if(temp > 30)
            System.out.println("it is hot outside!");
        else if(temp >= 20 && temp <= 30)
            System.out.println("it is warm outside!");
        else
            System.out.println("it is cold outside!");

        Scanner scan = new Scanner(System.in);

        System.out.println("You are playing a game! Press q or Q to quit.");
        String response = scan.next();

        if(response.equals("q") || response.equals("Q"))
            System.out.println("you quit the game.");
        else
            System.out.println("you are still playing the game. *pew pew*");

        scan.close();
    }
    public static void main(String[] args) {
        // variables();
        // string();
        // user_input();
        // expressions();
        //gui();
        //math();
        //findHypotenuse();
        //randomNumbers();
        //switchs();
        operators();
    }
}