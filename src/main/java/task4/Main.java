package task4;

import com.github.javafaker.Faker;

import java.util.Locale;
import java.util.Random;


public class Main {
    private static int numberOfUsers;
    private static String region;

    public static void main(String[] args) {
        numberOfUsers = Integer.parseInt(args[0]);
        region = args[1];
        switch (region) {
            case "ru_RU":
                ruFaker();
        }

    }

    private static void ruFaker() {
        Faker faker = new Faker(new Locale(region));
        String fullName;
        for (int i = 0; i < numberOfUsers; i++) {
            int randomInt = new Random().nextInt(2);
            if (randomInt == 0) {
                fullName = faker.expression("#{Name.male_first_name} #{Name.male_middle_name} #{Name.male_last_name}");
            } else
                fullName = faker.expression("#{Name.female_first_name} #{Name.female_middle_name} #{Name.female_last_name}");
            String zipCode = faker.address().zipCode();
            String address = faker.expression("#{Address.default_country}, #{Address.city}, #{Address.street_address} #{Address.secondary_address}");
            String phoneNumber = faker.phoneNumber().phoneNumber();
            System.out.println(fullName + "; " + zipCode + " " + address + "; " + phoneNumber);
        }
    }
}
