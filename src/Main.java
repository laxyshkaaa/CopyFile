import java.io.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Последовательное копирование: ");
        File source = new File("начальный.txt");
        File dest = new File("конечный.txt");
        copyFile(source, dest);
        File source2 = new File("начальный2.txt");
        File dest2 = new File("конечный2.txt");
        copyFile(source2, dest2);
        System.out.println("----------------------------------------------------------------------------------");
        System.out.println("Параллельное копирование: ");
        Thread thread1 = new Thread(() -> copyFile(new File("начальный.txt"), new File("конечный.txt")));
        Thread thread2 = new Thread(() -> copyFile(new File("начальныйThread.txt"), new File("конечныйThread.txt")));

        thread1.start();
        thread2.start();
    }

    private static void copyFile(File source, File dest) {
        long startTime = System.nanoTime(); // Начало отсчета времени

        try (InputStream is = new FileInputStream(source);
             OutputStream os = new FileOutputStream(dest)) {

            byte[] buffer = new byte[8192];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }

        } catch (IOException e) {
            System.err.println("Ошибка ввода-вывода: " + e.getMessage());
        }

        long duration = System.nanoTime() - startTime; // Вычисление времени выполнения
        System.out.println("Время выполнения копирования из " + source + " в " + dest + ": " + duration + " наносекунд");
    }
}
