package Nio;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class IoUtils {

    public static  void main(String[] args) throws IOException, InterruptedException {
        Path path = Paths.get("client");
        System.out.println(path);
        System.out.println(path.toAbsolutePath());


        WatchService watchService = FileSystems
                .getDefault()
                .newWatchService();

        path.register(watchService,
                StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_DELETE,
                StandardWatchEventKinds.ENTRY_MODIFY);

        Files.newBufferedReader(Paths.get("client", "12.txt"))
                .lines()
                .forEach(System.out::println);

        if (!Files.exists(Paths.get("client", "dir1"))) {
            Files.createDirectory(Paths.get("client", "dir1"));
        }

        Path p = Paths.get("client", "dir1", "1.txt");
        if (!Files.exists(p)) {
            Files.createFile(p);
        }

        Files.copy(Paths.get("client", "12.txt"), p, StandardCopyOption.REPLACE_EXISTING);
        Files.copy(Paths.get("client", "12.txt"),
                Paths.get("client/dir1/2.txt"),
                StandardCopyOption.REPLACE_EXISTING
        );

        Files.write(
                Paths.get("client/dir1/3.txt"),
                "Hello world".getBytes(),
                StandardOpenOption.APPEND
        );

        Files.walkFileTree(Paths.get("client"), new SimpleFileVisitor() {
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                System.out.println(file.toAbsolutePath());
                return super.visitFile(file, attrs);
            }
        });

        Files.find(Paths.get("client"),
                Integer.MAX_VALUE, (path1, basicFileAttributes) -> path1.getFileName().toString()
                        .equals("3.txt")).forEach(System.out::println);

    }
}

