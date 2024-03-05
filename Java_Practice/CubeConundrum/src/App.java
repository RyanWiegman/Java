import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App {
    public static void main(String[] args) throws Exception {
        File file = new File("C:\\workspace\\Java\\Java_Practice\\CubeConundrum\\lib\\input.txt");
        Scanner scanner;
        String inputString;
        String gameString = "";
        int goodGame = 0;
        int total = 0;

        
        if(file != null)
            scanner = new Scanner(file);
        else
            return;

        while(scanner.hasNextLine()) {
            inputString = scanner.nextLine();
            System.out.println("input string: " + inputString);
            int splitIndex = inputString.indexOf(":");

            if(splitIndex != -1) {
                gameString = inputString.substring(0, splitIndex);
                String subString = inputString.substring(splitIndex + 1);
                System.out.println("gameString: " + gameString);
                System.out.println("substring: " + subString);
                goodGame = getNumbers(subString);
            }

            if(goodGame == 1) {
                System.out.println(gameString + " is good game...");
                Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
                Matcher matcher = pattern.matcher(gameString);

                while (matcher.find()) {
                    Integer gameNumber = Integer.parseInt(matcher.group());
                    total += gameNumber;
                    System.out.println("total score: " + total);
                }
            }
        }

        System.out.println("final score: " + total);
        scanner.close();
    }

    protected static int getNumbers(String input) {
        Pattern pattern = Pattern.compile("(\\d+)\\s(\\w+)");
        Matcher matcher = pattern.matcher(input);

        while(matcher.find()) {
            Integer number = Integer.parseInt(matcher.group(1));
            String color = matcher.group(2);
            System.out.println("color: " + color + " adding: " + number + " to list...");

            if(number > 15) {
                return 0;
            }
            if(color.equalsIgnoreCase("blue") && number > 14) {
                return 0;
            }
            if(color.equalsIgnoreCase("green") && number > 13) {
                return 0;
            }
            if(color.equalsIgnoreCase("red") && number > 12) {
                return 0;
            }
        }

        return 1;
    }
}
