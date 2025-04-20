package exercise;

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
    private static Path getFullPath(String filePath) {
        return Paths.get(filePath).toAbsolutePath().normalize();
    }
    public static CompletableFuture<String> unionFiles(String pathToFile1, String pathToFile2, String pathToDestFile)  {

        CompletableFuture<String> futureContentFromFile1 =
                CompletableFuture.supplyAsync(() -> {
                    String content1 = "";
                    try {
                        content1 = Files.readString(getFullPath(pathToFile1));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    return content1;
                });

        CompletableFuture<String> futureContentFromFile2 =
                CompletableFuture.supplyAsync(() -> {
                    String content1 = "";
                    try {
                        content1 = Files.readString(getFullPath(pathToFile2));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    return content1;
                });

        return futureContentFromFile1.thenCombine(
                futureContentFromFile2,
                (content1, content2) -> {
                    String content = content1 + content2;
                    try {
                        Files.writeString(
                                getFullPath(pathToDestFile),
                                content,
                                StandardCharsets.UTF_8,
                                StandardOpenOption.CREATE
                        );
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    return "ok!)";
                }
        ).exceptionally(ex -> {
            System.out.println(ex.getMessage());
            return "Unknown!(";
        });
    }
    // END

    public static CompletableFuture<Long> getDirectorySize(String pathToDir) {
        File directory = new File(pathToDir);

        if (!directory.isDirectory()) {
            return CompletableFuture.completedFuture(0L);
        }

        File[] files = directory.listFiles();
        CompletableFuture<Long>[] sizeFiles = Arrays.stream(files)
                .filter(File::isFile)
                .map(file -> CompletableFuture.supplyAsync(() -> file.length()))
                .toArray(CompletableFuture[]::new);

        return CompletableFuture.allOf(sizeFiles)
                .thenApply((array) -> Arrays.stream(sizeFiles)
                        .mapToLong(CompletableFuture::join)
                        .sum());
    }

    public static void main(String[] args) throws Exception {
        // BEGIN
        CompletableFuture<String> result = unionFiles(
                "src/main/resources/file1.txt",
                "src/main/resources/file2.txt",
                "src/main/resources/dest.txt"
        );
        CompletableFuture<Long> size = getDirectorySize("src/main/resources");
        result.get();
        System.out.println("done!");
        System.out.println(size.get());
        // END
    }
}

