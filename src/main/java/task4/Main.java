package task4;

import com.github.javafaker.Faker;

import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        
        Faker faker = new Faker(new Locale("ru_RU"));
        String zipCode = faker.address().zipCode();
        String address = faker.address().streetAddress();
        String name = faker.name().fullName();
        String telNumber = faker.phoneNumber().phoneNumber();
        System.out.println(name + "; " + zipCode + " " + address + "; " + telNumber);

    }
}

