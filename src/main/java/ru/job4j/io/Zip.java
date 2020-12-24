package ru.job4j.io;

import com.sun.jdi.event.ExceptionEvent;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<Path> sources, String target, Path parentDir) {
            try (ZipOutputStream zip = new ZipOutputStream(
                    new BufferedOutputStream(new FileOutputStream(target)))) {
                for (Path file : sources) {
                    zip.putNextEntry(new ZipEntry(parentDir.relativize(file).toString()));
                    zip.write(Files.readAllBytes(file));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(
                new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(in.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ArgZip zipArgs = new ArgZip(args);
        if (!zipArgs.valid()) {
            throw new IllegalArgumentException("Arguments are not valid");
        }
        List<Path> sources;
        Path dir = Paths.get(zipArgs.directory());
        try {
            sources = Search.search(dir, zipArgs.exclude());
            new Zip().packFiles(sources, zipArgs.output(), dir);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}