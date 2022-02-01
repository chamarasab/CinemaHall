import java.util.Scanner;

public class App {
    static int rows = 0;
    static int columns = 0;
    static char seats[][];
    static int profit = 0;
    static int currentIncome = 0;
    static int noOfTicketsPurchased = 0;

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of rows:");
        rows = scanner.nextInt();

        System.out.println("Enter the number of seats in each row:");
        columns = scanner.nextInt();

        if (rows <= 9 && columns <= 9) {
            seats = new char[rows + 1][columns + 1];
            defineSeats(seats);
            checkProfit(rows, columns);
            menu();
        } else {
            System.out.println("Enter 1 - 9 numbers");
        }

        scanner.close();
    }

    private static void menu() {
        System.out.println();
        Scanner scanner = new Scanner(System.in);

        System.out.println("1. Show the seats\n2. Buy a ticket\n3. Statistics\n0. Exit");
        int option = scanner.nextInt();

        switch (option) {
            case 1:
                showSeats(seats);
                break;
            case 2:
                bookSeat();
                break;
            case 3:
                statistics();
                break;
            case 0:
                break;
            default:
                System.out.println("Invalid input");
        }

        scanner.close();
    }

    private static void statistics() {
        System.out.println("\nNumber of purchased tickets: " + noOfTicketsPurchased);

        System.out.print("Percentage: ");
        System.out.printf("%.2f", percent());
        System.out.println("%");

        System.out.println("Current income: $" + currentIncome);
        System.out.println("Total income: $" + profit);

        menu();
    }

    private static float percent() {
        float noOfSeats = rows * columns;
        float per = (noOfTicketsPurchased / noOfSeats) * 100.00f;
        return per;
    }

    private static void bookSeat() {
        System.out.println();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a row number:");
        int row = scanner.nextInt();

        System.out.println("Enter a seat number in that row:");
        int column = scanner.nextInt();

        priceOfSeat(row, column);

        if (row <= rows && column <= columns) {
            if (seats[row][column] == 'S') {
                seats[row][column] = 'B';
                noOfTicketsPurchased += 1;
                menu();
            } else {
                System.out.println("Sold seat");
                bookSeat();
            }
        } else {
            System.out.println("Wrong input!");
            bookSeat();
        }

        scanner.close();
    }

    private static void priceOfSeat(int row, int column) {
        int noOfSeats = rows * columns;
        int price = 0;

        if (noOfSeats <= 60) {
            price = 10;
        } else if (noOfSeats > 60) {
            int half = rows / 2;
            if (row <= half) {
                price = 10;
            } else if (row > half) {
                price = 8;
            }
        }

        currentIncome += price;
        System.out.println("Ticket price: $" + price);
    }

    private static void checkProfit(int rows, int columns) {
        int noOfSeats = rows * columns;

        if (noOfSeats <= 60) {
            profit = noOfSeats * 10;
        } else if (noOfSeats > 60) {
            int firstHalf = ((rows / 2) * columns) * 10;
            int backHalf = ((rows - (rows / 2)) * columns) * 8;
            profit = firstHalf + backHalf;
        }
    }

    private static void showSeats(char[][] seats) {
        System.out.println("\nCinema:");

        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                System.out.print(seats[i][j] + " ");
            }
            System.out.println();
        }

        menu();
    }

    private static void defineSeats(char[][] seats) {
        seats[0][0] = ' ';

        for (int i = 1; i < seats.length; i++) {
            // horizontal number
            for (int j = 1; j < seats[i].length; j++) {
                char a = (char) (j + '0');
                seats[0][j] = a;
            }
            // vertical number
            for (int j = 1; j <= rows; j++) {
                char a = (char) (j + '0');
                seats[j][0] = a;
            }
            // middle 'S'
            for (int j = 1; j < seats[i].length; j++) {
                seats[i][j] = 'S';
            }
        }
    }
}
