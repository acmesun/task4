package task4;

import com.github.javafaker.Faker;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class Main {
    private static final CSVFormat CSV_FORMAT = CSVFormat.DEFAULT.withDelimiter(';');
    private static final Random RANDOM = new Random();
    private static int numberOfUsers;
    private static String region;

    public static void main(String[] args) throws IOException {
        numberOfUsers = Integer.parseInt(args[0]);
        region = args[1];
        if (numberOfUsers <= 0) {
            System.out.println("Exception. Number of users can not be " + numberOfUsers);
        } else if (region.equals("ru_RU") || region.equals("uk_UA")) {
            printUsers(generateSlavic());
        } else if (region.equals("en_US")) {
            printUsers(generateAmericans());
        } else {
            System.out.println("Exception. Region is wrong");
        }
    }


    private static List<User> generateSlavic() {
        Faker faker = new Faker(new Locale(region));
        return IntStream.range(0, numberOfUsers)
                .parallel()
                .mapToObj(o -> new User(
                        generateSlavicName(faker),
                        generateSlavicAddress(faker),
                        faker.phoneNumber().cellPhone()))
                .collect(Collectors.toList());
    }

    private static String generateSlavicAddress(Faker faker) {
        return faker.expression("#{Address.default_country}, #{Address.city}")
                + ", "
                + faker.address().zipCode()
                + ", "
                + faker.expression("#{Address.street_address} #{Address.secondary_address}");
    }

    private static String generateSlavicName(Faker faker) {
        String fullName;
        int randomInt = RANDOM.nextInt(2);
        if (randomInt == 0) {
            fullName = faker.expression("#{Name.male_first_name} #{Name.male_middle_name} #{Name.male_last_name}");
        } else {
            fullName = faker.expression("#{Name.female_first_name} #{Name.female_middle_name} #{Name.female_last_name}");
        }
        return fullName;
    }

    private static List<User> generateAmericans() {
        Faker faker = new Faker(new Locale(region));
        return IntStream.range(0, numberOfUsers)
                .parallel()
                .mapToObj(o -> new User(
                        faker.expression("#{Name.first_name} #{Name.last_name}"),
                        faker.expression("#{Address.default_country}, #{Address.zip_code}, #{Address.city}, #{Address.street_address}"),
                        faker.phoneNumber().cellPhone()))
                .collect(Collectors.toList());
    }

    private static void printUsers(List<User> users) throws IOException {
        try (CSVPrinter csvPrinter = new CSVPrinter(System.out, CSV_FORMAT)) {
            for (User user : users) {
                csvPrinter.printRecord(user.name, user.address, user.phoneNumber);
            }
        }
    }

    private static class User {
        private final String name;
        private final String address;
        private final String phoneNumber;

        public User(String name, String address, String phoneNumber) {
            this.name = name;
            this.address = address;
            this.phoneNumber = phoneNumber;
        }
    }
}
