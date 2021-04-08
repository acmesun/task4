package task4;

import com.github.javafaker.Address;
import com.github.javafaker.Faker;

import java.util.Locale;


public class Main {
    public static void main(String[] args) {
        int numberOfUsers = Integer.parseInt(args[0]);
        String region = args[1];
        for (int i = 0; i < numberOfUsers; i++) {
            Faker faker = new Faker(new Locale(region));
            String fullName = faker.name().fullName();
            Address address = faker.address();
            String code = address.zipCode();
            String country = address.country();
            String streetAddress = address.streetAddress();
            String houseNumber = address.buildingNumber();
            String phoneNumber = faker.phoneNumber().phoneNumber();
            System.out.println(fullName + "; " + code + " " + country + ", " + streetAddress + ", " + houseNumber + "; " + phoneNumber);
        }
    }
}

/*Faker faker = new Faker(new Locale("ru_RU"));
        String zipCode = faker.address().zipCode();
        String address = faker.address().streetAddress();
        String name = faker.name().fullName();
        String telNumber = faker.phoneNumber().phoneNumber();
        System.out.println(name + "; " + zipCode + " " + address + "; " + telNumber); */