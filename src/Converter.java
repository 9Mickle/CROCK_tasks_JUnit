import java.util.Scanner;

public class Converter {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите рамзер файла в байтах: ");
        float sizeInBytes = scanner.nextLong();

        printBytes(sizeInBytes);
    }

    public static void printBytes(float sizeInBytes){

        float kB = 1024;
        float mB = 1024 * kB;
        float gB = 1024 * mB;
        float tB = 1024 * gB;

        if (sizeInBytes < kB)
            System.out.println("\nКонвертированный размер: " +
                    String.format("%.1f", sizeInBytes) + " B");

        else if (sizeInBytes >= kB && sizeInBytes < mB)
            System.out.println("\nКонвертированный размер: " +
                    String.format("%.1f", sizeInBytes/kB) + " KB");

        else if (sizeInBytes >= mB && sizeInBytes < gB)
            System.out.println("\nКонвертированный размер: " +
                    String.format("%.1f", sizeInBytes/mB) + " MB");

        else if (sizeInBytes >= gB && sizeInBytes < tB)
            System.out.println("\nКонвертированный размер: " +
                    String.format("%.1f", sizeInBytes/gB) + " GB");

        else
            System.out.println("\nКонвертированный размер: " +
                    String.format("%.1f", sizeInBytes/tB) + " TB");
    }
}
