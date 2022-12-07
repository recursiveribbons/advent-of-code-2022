package dev.robinsyl;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class FileLoader {
    private FileLoader() {}

    public static String asString(String filename) {
        return asStream(filename)
                .collect(Collectors.joining("\n"));
    }

    public static List<String> asList(String filename) {
        try {
            return Files.readAllLines(Paths.get(getURI(filename)));
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static Stream<String> asStream(String filename) {
        try {
            return Files.lines(Paths.get(getURI(filename)));
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private static URI getURI(String filename) {
        try {
            return Objects.requireNonNull(FileLoader.class.getClassLoader()
                            .getResource(filename))
                    .toURI();
        } catch (URISyntaxException | NullPointerException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
