package task4;

import com.github.javafaker.Faker;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.IOException;
import java.util.Locale;
import java.util.Random;


public class Main {
    private static final CSVFormat CSV_FORMAT = CSVFormat.DEFAULT.withDelimiter(';');
    private static int numberOfUsers;
    private static String region;

    public static void main(String[] args) throws IOException {
        numberOfUsers = Integer.parseInt(args[0]);
        region = args[1];
        if (numberOfUsers <= 0) {
            System.out.println("Exception. Number of users can not be " + numberOfUsers);
        } else if (region.equals("ru_RU") || region.equals("uk_UA")) {
            russianOrUkraineFaker();
        } else if (region.equals("en_US")) {
            americanFaker();
        } else System.out.println("Exception. Region is wrong");
    }

    private static void russianOrUkraineFaker() throws IOException {
        Faker faker = new Faker(new Locale(region));
        try (CSVPrinter csvPrinter = new CSVPrinter(System.out, CSV_FORMAT)) {
            for (int i = 0; i < numberOfUsers; i++) {
                String fullName;
                int randomInt = new Random().nextInt(2);
                if (randomInt == 0) {
                    fullName = faker.expression("#{Name.male_first_name} #{Name.male_middle_name} #{Name.male_last_name}");
                } else
                    fullName = faker.expression("#{Name.female_first_name} #{Name.female_middle_name} #{Name.female_last_name}");
                String zipCode = faker.address().zipCode();
                String address = faker.expression("#{Address.default_country}, #{Address.city}, #{Address.street_address} #{Address.secondary_address}");
                String phoneNumber = faker.phoneNumber().phoneNumber();
                csvPrinter.printRecord(fullName, zipCode, address, phoneNumber);
            }
        }
    }


    private static void americanFaker() throws IOException {
        Faker faker = new Faker(new Locale(region));
        try (CSVPrinter csvPrinter = new CSVPrinter(System.out, CSV_FORMAT)) {
            for (int i = 0; i < numberOfUsers; i++) {
                String fullName = faker.expression("#{Name.first_name} #{Name.last_name}");
                String address = faker.expression("#{Address.default_country}, #{Address.zip_code}, #{Address.city}, #{Address.street_address}");
                String phoneNumber = faker.phoneNumber().cellPhone();
                csvPrinter.printRecord(fullName, address, phoneNumber);
            }
        }
    }
}