package com.cesarwillymc.libraryprojectmpp.data.local.util;



import com.cesarwillymc.libraryprojectmpp.data.local.StorageType;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.StringJoiner;

public class FileUtil {
    public static final String slashSeparator = FileSystems.getDefault().getSeparator();
    public static final String pathProject = new StringJoiner(slashSeparator, slashSeparator, "")
            .add("src").add("main").add("java").add("com").add("cesarwillymc").add("libraryprojectmpp").add("data").add("local").add("storage").toString();
    public static final String OUTPUT_DIR_PROJECT = System.getProperty("user.dir") + pathProject;

    public static <T extends HashMap<?,?>> T readFromStorage(StorageType type) {
        try (var in = new ObjectInputStream(Files.newInputStream(FileSystems.getDefault().getPath(OUTPUT_DIR_PROJECT, type.toString())))) {
            return (T) in.readObject();
        } catch (Exception e) {
            saveToStorage(type,(T) new HashMap<>());
            return (T) new HashMap<>();
        }
    }

    @SuppressWarnings("rawtypes")
    public static <T extends HashMap> void saveToStorage(StorageType type, T ob) {
        try (var out = new ObjectOutputStream(Files.newOutputStream(FileSystems.getDefault().getPath(OUTPUT_DIR_PROJECT, type.toString())))) {
            out.writeObject(ob);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
