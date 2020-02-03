package justin.nio;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

class ShowFileTest {

	public static void main(String[] args) throws IOException {
		Path initPath = Paths.get("/Users/justin/Downloads");
		Files.walkFileTree(initPath, new SimpleFileVisitor<Path>() {
			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
				System.out.println(file.getFileName().toString());
				return FileVisitResult.CONTINUE;
			}
		});
	}
}
