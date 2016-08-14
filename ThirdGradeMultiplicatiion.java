import java.util.Scanner;

class ThirdGradeMultiplication {
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        String numberA, numberB;
        long product = 0, loopProduct = 0;
        // Input two numbers
        numberA = scanner.nextLine();
        numberB = scanner.nextLine();
        int number = Integer.parseInt(numberA);
        for(int i=0; i<numberA.length(); ++i) {
            loopProduct = number * Character.getNumericValue(numberB.charAt(numberB.length() - i -1));
            loopProduct = loopProduct * (int)Math.pow(10, i);
            product += loopProduct;
            System.out.println(loopProduct);
        }
        System.out.println("Product : " + product);
    }
}
