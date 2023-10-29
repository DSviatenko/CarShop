package com.buycar.controller.util;

import com.buycar.entity.Fuel;
import com.buycar.entity.Role;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TestHelper {

    public static final String USER_EMPTY_NAME_JSON =
            "{" +
                    "\"name\": \"\"," +
                    "\"role\":\"USER\"," +
                    "\"email\": \"mikemail@gmail.com\"," +
                    "\"password\": \"topsecret\"" +
                    "}";

    public static final String USER_INCORRECT_ROLE_JSON =
            "{" +
                    "\"name\": \"Mike\"," +
                    "\"role\":\"ADMINISTRATOR\"," +
                    "\"email\": \"mikelucky@gmail.com\"," +
                    "\"password\": \"123456\"" +
                    "}";

    public static final String USER_JSON =
            "{" +
                    "\"name\": \"Adam\"," +
                    "\"role\":\"USER\"," +
                    "\"email\": \"adam_23@gmail.com\"," +
                    "\"password\": \"adam55\"" +
                    "}";

    public static final String USER_WITH_ID_JSON =
            "{" +
                    "\"id\": %s," +
                    "\"name\": \"Tomas\"," +
                    "\"role\":\"USER\"," +
                    "\"email\": \"adam_23@gmail.com\"," +
                    "\"password\": \"adam55\"" +
                    "}";

    public static final String USER_INCORRECT_EMAIL_JSON =
            "{" +
                    "\"name\": \"Mike\"," +
                    "\"role\":\"USER\"," +
                    "\"email\": \"mikeluckygmail.com\"," +
                    "\"password\": \"123456\"" +
                    "}";

    public static final String CAR_EMPTY_MODEL_JSON =
            "{" +
                    "\"ownerId\": 1," +
                    "\"brand\":\"Opel\"," +
                    "\"model\": \"\"," +
                    "\"engine_capacity\": 1598," +
                    "\"fuel\": \"GASOLINE\"," +
                    "\"year\": 1996," +
                    "\"price\": 4000" +
                    "}";

    public static final String CAR_EMPTY_USER_JSON =
            "{" +
                    "\"ownerId\": ," +
                    "\"brand\":\"Opel\"," +
                    "\"model\": \"Vectra B\"," +
                    "\"engine_capacity\": 1598," +
                    "\"fuel\": \"GASOLINE\"," +
                    "\"year\": 1996," +
                    "\"price\": 4000" +
                    "}";

    public static final String CAR_WITH_ID_JSON =
            "{" +
                    "\"id\": %s," +
                    "\"ownerId\": 1," +
                    "\"brand\":\"Mercedes-Benz\"," +
                    "\"model\": \"E-class\"," +
                    "\"engineCapacity\": 3200," +
                    "\"fuel\": \"DIESEL\"," +
                    "\"year\": 2001," +
                    "\"price\": 5000" +
                    "}";

    public static final String CAR_INCORRECT_USER_JSON =
            "{" +
                    "\"ownerId\": \"user\"," +
                    "\"brand\":\"Opel\"," +
                    "\"model\": \"Vectra B\"," +
                    "\"engine_capacity\": 1598," +
                    "\"fuel\": \"GASOLINE\"," +
                    "\"year\": 1996," +
                    "\"price\": 4000" +
                    "}";

    public static final String CAR_INCORRECT_FUEL_JSON =
            "{" +
                    "\"ownerId\": \"3\"," +
                    "\"brand\":\"Opel\"," +
                    "\"model\": \"Vectra B\"," +
                    "\"engine_capacity\": 1598," +
                    "\"fuel\": \"GASOLIN\"," +
                    "\"year\": 1996," +
                    "\"price\": 4000" +
                    "}";

    public static final String CAR_JSON =
            "{" +
                    "\"ownerId\": 2," +
                    "\"brand\":\"Volkswagen\"," +
                    "\"model\": \"Passat\"," +
                    "\"engineCapacity\": 1998," +
                    "\"fuel\": \"DIESEL\"," +
                    "\"year\": 2014," +
                    "\"price\": 10000" +
                    "}";

    private final List<UserInfoTest> allUsers = new ArrayList<>();

    private final List<CarInfoTest> allCars = new ArrayList<>();

    private List<CarInfoTest> allFirstUserCars = new ArrayList<>();

    public TestHelper() {
        try {
            allUsers.add(new UserInfoTest(1L, "John", Role.USER, "john1@gmail.com", "johnpass"));
            allUsers.add(new UserInfoTest(2L, "Bob", Role.USER, "hunter_s@gmail.com", "bobishunter1"));
            allUsers.add(new UserInfoTest(3L, "Tom", Role.USER, "spider@gmail.com", "12345678"));
            allUsers.add(new UserInfoTest(4L, "Mike", Role.USER, "mk_32@gmail.com", "secretpass"));
            allUsers.add(new UserInfoTest(5L, "Anthony", Role.USER, "anderson@gmail.com", "232356"));

            allCars.add(new CarInfoTest(1L, 1L, "Opel", "Vectra B", 1598, Fuel.GASOLINE, 1996, 4000));
            allCars.add(new CarInfoTest(2L, 1L, "Mercedes-Benz", "E-class", 3200, Fuel.DIESEL, 2001, 5000));
            allCars.add(new CarInfoTest(3L, 1L, "BMW", "5 series", 2498, Fuel.GASOLINE, 1993, 3700));
            allCars.add(new CarInfoTest(4L, 2L, "Tesla", "Model 3", 0, Fuel.ELECTRIC, 2020, 27000));
            allCars.add(new CarInfoTest(5L, 3L, "Audi", "A6", 3000, Fuel.DIESEL, 2015, 19000));

            allFirstUserCars = allCars.stream().filter(x -> (x.owner == 1)).collect(Collectors.toList());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<UserInfoTest> getAllUsers() {
        return allUsers;
    }

    public List<CarInfoTest> getAllCars() {
        return allCars;
    }

    public List<CarInfoTest> getAllFirstUserCars() {
        return allFirstUserCars;
    }

    public UserInfoTest getUserInfoById(long id) {
        return allUsers.stream().filter(x -> (x.id == id)).findFirst().orElse(null);
    }

    public CarInfoTest getCarInfoById(long id) {
        return allCars.stream().filter(x -> (x.id == id)).findFirst().orElse(null);
    }
}
