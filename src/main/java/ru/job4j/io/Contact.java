package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class Contact implements Serializable {

    private final String name;
    private final String number;

    public Contact(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", number='" + number + '\'' +
                '}';
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Contact contact1 = new Contact("Alex", "+8 888 88 88");
        Contact contact2 = new Contact("Vlada", "+7 777 77 77");
        System.out.println("Before: ");
        System.out.println(contact1);
        System.out.println(contact2);
        File tempFile = Files.createTempFile(null, null).toFile();


        try (FileOutputStream fos = new FileOutputStream(tempFile);
             ObjectOutputStream oos = new ObjectOutputStream(fos)){
            oos.writeObject(contact1);
            oos.writeObject(contact2);
        }
        try (FileInputStream fis = new FileInputStream(tempFile);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            Contact contact3 = (Contact) ois.readObject();
            Contact contact4 = (Contact) ois.readObject();
            System.out.println("After:");
            System.out.println(contact3);
            System.out.println(contact4);
        }
    }
}
