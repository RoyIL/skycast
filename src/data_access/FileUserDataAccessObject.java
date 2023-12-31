package data_access;

import entity.CommonUser;
import entity.User;
import use_case.createNotification.CreateNotificationDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.setsettings.SetSettingsDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;
//import use_case.createNotification.CreateNotificationDataAccessInterface;

import java.io.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class FileUserDataAccessObject implements SignupUserDataAccessInterface, LoginUserDataAccessInterface,
        SetSettingsDataAccessInterface, CreateNotificationDataAccessInterface {
        //,CreateNotificationDataAccessInterface

    private final File csvFile;

    private final Map<String, Integer> headers = new LinkedHashMap<>();

    private final Map<String, User> accounts = new HashMap<>();

    public FileUserDataAccessObject(String csvPath) throws IOException {
        csvFile = new File(csvPath);
        headers.put("username", 0);
        headers.put("password", 1);
        headers.put("creation_time", 2);
        headers.put("phone_number", 3);

        if (csvFile.length() == 0) {
            save();
        } else {

            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                String header = reader.readLine();

                // For later: clean this up by creating a new Exception subclass and handling it in the UI.
                assert header.equals("username,password,creation_time,phone_number");

                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",");
                    String username = String.valueOf(col[headers.get("username")]);
                    String password = String.valueOf(col[headers.get("password")]);
                    String creationTimeText = String.valueOf(col[headers.get("creation_time")]);
                    String phoneNumber = String.valueOf(col[headers.get("phone_number")]);
                    LocalDateTime ldt = LocalDateTime.parse(creationTimeText);
                    User user = new CommonUser(username, password, ldt, phoneNumber);
                    accounts.put(username, user);
                }
            }
        }
    }

    @Override
    public void save(User user) {
        accounts.put(user.getUsername(), user);
        this.save();
    }

    @Override
    public User get(String username) {
        return accounts.get(username);
    }

    @Override
    public String getPhoneNumber(String username) { return accounts.get(username).getPhoneNumber();}

    private void save() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvFile));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            for (User user : accounts.values()) {
                String line = String.format("%s,%s,%s,%s",
                        user.getUsername(), user.getPassword(), user.getCreationTime(), user.getPhoneNumber());
                writer.write(line);
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Return whether a user exists with username identifier.
     * @param identifier the username to check.
     * @return whether a user exists with username identifier
     */
    @Override
    public boolean exists(String identifier) {
        return accounts.containsKey(identifier);
    }

    public String getCsvFilePath() {
        return csvFile.getPath();
    }

}