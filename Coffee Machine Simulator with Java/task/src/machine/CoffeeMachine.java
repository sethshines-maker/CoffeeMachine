package machine;

import java.util.Scanner;

public class CoffeeMachine {

    static int water = 400;
    static int milk = 540;
    static int beans = 120;
    static int cups = 9;
    static int money = 550;
    static int totalCups = 10;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Write action (buy, fill, take, clean, remaining, exit):");
            String action = scanner.next();

            if (action.equals("exit")) {
                break;
            }

            switch (action) {
                case "buy":
                    if(totalCups == 0) {
                        System.out.println("I need cleaning!");
                        break;
                    }
                    buy(scanner);
                    break;
                case "fill":
                    fill(scanner);
                    break;
                case "take":
                    take();
                    break;
                case "clean":
                    totalCups = 10;
                    System.out.println("I have been cleaned!");
                    break;
                case "remaining":
                    printState();
                    break;
                default:
                    System.out.println("Unknown action");
            }
        }
    }

    // ---------------- ACTION FUNCTIONS ----------------

    static void buy(Scanner scanner) {
        System.out.println(
                "What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
        String choice = scanner.next();

        if (choice.equals("back")) {
            return;
        }

        int reqWater = 0, reqMilk = 0, reqBeans = 0, price = 0;

        switch (choice) {
            case "1": // espresso
                reqWater = 250;
                reqBeans = 16;
                price = 4;
                break;
            case "2": // latte
                reqWater = 350;
                reqMilk = 75;
                reqBeans = 20;
                price = 7;
                break;
            case "3": // cappuccino
                reqWater = 200;
                reqMilk = 100;
                reqBeans = 12;
                price = 6;
                break;
            default:
                System.out.println("Unknown coffee type");
                return;
        }

        // Check resources
        if (water < reqWater) {
            System.out.println("Sorry, not enough water!");
            return;
        }
        if (milk < reqMilk) {
            System.out.println("Sorry, not enough milk!");
            return;
        }
        if (beans < reqBeans) {
            System.out.println("Sorry, not enough coffee beans!");
            return;
        }
        if (cups < 1) {
            System.out.println("Sorry, not enough disposable cups!");
            return;
        }

        // If all good
        System.out.println("I have enough resources, making you a coffee!");
        totalCups--;
        water -= reqWater;
        milk -= reqMilk;
        beans -= reqBeans;
        cups--;
        money += price;
    }

    static void fill(Scanner scanner) {
        System.out.println("Write how many ml of water you want to add:");
        water += scanner.nextInt();

        System.out.println("Write how many ml of milk you want to add:");
        milk += scanner.nextInt();

        System.out.println("Write how many grams of coffee beans you want to add:");
        beans += scanner.nextInt();

        System.out.println("Write how many disposable cups you want to add:");
        cups += scanner.nextInt();
    }

    static void take() {
        System.out.println("I gave you $" + money);
        money = 0;
    }

    // ---------------- HELPER FUNCTIONS ----------------

    static void printState() {
        System.out.println("\nThe coffee machine has:");
        System.out.println(water + " ml of water");
        System.out.println(milk + " ml of milk");
        System.out.println(beans + " g of coffee beans");
        System.out.println(cups + " disposable cups");
        System.out.println("$" + money + " of money\n");
    }
}
