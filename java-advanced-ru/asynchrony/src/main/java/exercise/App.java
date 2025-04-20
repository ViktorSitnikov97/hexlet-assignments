package exercise;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;
import java.util.Arrays;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import java.io.File;
import java.nio.file.StandardOpenOption;

class App {

    // BEGIN
    public static CompletableFuture<String> unionFiles(String pathToFile1, String pathToFile2, String pathToDestFile)  {

        CompletableFuture<String> futureContentFromFile1 =
                CompletableFuture.supplyAsync(() -> {
                    String content1 = null;
                    try {
                        content1 = getData(pathToFile1);
                    } catch (Exception e) {
                        System.out.println("NoSuchFileException");
                    }
                    return content1;
                });

        CompletableFuture<String> futureContentFromFile2 =
                CompletableFuture.supplyAsync(() -> {
                    String content1 = null;
                    try {
                        content1 = getData(pathToFile2);
                    } catch (Exception e) {
                        System.out.println("NoSuchFileException");
                    }
                    return content1;
                });

        return futureContentFromFile1.thenCombine(
                futureContentFromFile2,
                (content1, content2) -> {
                    try {
                        writeToDestFile(pathToDestFile, content1);
                        writeToDestFile(pathToDestFile, content2);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    return content1 + content2;
                }
        ).exceptionally(ex -> {
            System.out.println(ex.getMessage());
            return null;
        });
    }

    private static String getData(String pathToFile) throws IOException {
        Path path = Paths.get(pathToFile).toAbsolutePath().normalize();
        File file = new File(path.toString());
        if (!file.exists()){
            throw new RuntimeException();
        }
        return Files.readString(path);
    }
    private static void writeToDestFile(String pathToDestFile, String content) throws IOException {
        Path path = Paths.get(pathToDestFile).toAbsolutePath().normalize();
        Files.writeString(path, content, StandardCharsets.UTF_8, StandardOpenOption.CREATE,
                StandardOpenOption.APPEND);
    }
    // END

    private static File[] getFiles(Path pathToDir) {
        File dir = new File(pathToDir.toAbsolutePath().normalize().toString());
        File[] files = dir.listFiles();
        int size = 0;
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    size++;
                }
            }
            return Arrays.copyOfRange(files, 0, size + 1);
        }
        return null;
    }
    public static void main(String[] args) throws Exception {
        // BEGIN
        
        // END
    }
}

