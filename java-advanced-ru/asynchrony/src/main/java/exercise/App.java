package exercise;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.Arrays;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import java.io.File;
import java.nio.file.StandardOpenOption;

class App {

    // BEGIN
    public static CompletableFuture<String> unionFiles(String path1, String path2, String path3) {
      return CompletableFuture.supplyAsync(() -> {
          try {
              System.out.println(" ------------------ ");
              String content1 = Files.readString(Paths.get(path1));
              String content2 = Files.readString(Paths.get(path2));
              String unionContent = content1 + System.lineSeparator() + content2;
              Files.writeString(Paths.get(path3), unionContent, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
              return unionContent;

          } catch (IOException e) {
              e.printStackTrace(System.out);
              return "";
          }


      });
    }

    
    // END

    public static void main(String[] args) throws Exception {
        // BEGIN
        var  file1 = "src/main/resources/file1.txt";
        var file2 = "src/main/resources/file2.txt";
        var outputFile = "src/main/resources/output.txt";

        unionFiles(file1, file2, outputFile)
                .thenRun(() -> System.out.println("Файлы объединены успешно!"))
                .exceptionally(ex -> {
                    System.err.println("Ошибка: " + ex.getMessage());
                    return null;
                });
        // END
    }
}

