package others;

import java.util.Scanner;

public class FooBarBaz {

    // It is a good idea to add static constants at the top
    private static final String STOP_COMMAND = "stop";

    // standard name is args for string array in main method
    public static void main(String[] args) {
        StringBuilder argument = new StringBuilder();

        if (args.length > 0) {
            argument.append(args[0]);
        }

        String mode = argument.toString();

        if (mode.equals("console") || mode.equals("")) {
            Scanner sc = new Scanner(System.in);

            while (true) {
                System.out.println("What number would you like to count up to: ");

                try {
                    String playerInput = sc.nextLine();

                    if (playerInput.equalsIgnoreCase(STOP_COMMAND)) {
                        System.out.println("Stop Command is passed");
                        System.exit(0);
                    }

                    int userInput = Integer.parseInt(playerInput);

                    // Instead of while lopp, if will suffice
                    if (userInput < 0) {
                        System.out.println("Invalid Input!");
                        continue;
                    }
                    for (int i = 0; i < userInput + 1; i++) {
                        int consoleMatchCount = 0;
                        if (i == 0) System.out.print(i);
                        else {
                            if (i % 3 == 0) {
                                System.out.print("foo");
                                consoleMatchCount++;
                            }
                            if (i % 5 == 0) {
                                System.out.print("bar");
                                consoleMatchCount++;
                            }
                            if (i % 7 == 0) {
                                System.out.print("baz");
                                consoleMatchCount++;
                            }
                            if (consoleMatchCount == 0) {
                                System.out.print(i);
                            }
                        }
                        System.out.println();
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    System.out.println("Invalid Input!");
                }

            }
        }
    }
}
