package crmpp.csvreader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import crmpp.Model;
import crmpp.UserData;
import crmpp.csvreader.entities.Availability;
import crmpp.csvreader.entities.Interest;
import crmpp.csvreader.entities.User;


public class PersistCSVElements {

    protected Map<Integer, UserData> persistUsers(List<User> objects) throws Exception {
        HashMap<Integer, UserData> users = new HashMap<>();
        for (User row : objects) {
            users.put(row.id, new UserData(row.id, row.firstName, row.lastName, row.gender, row.age, null, null, null));
        }
        return users;
    }

    protected Map<Integer, UserData> persistInterests(List<Interest> objects, Map<Integer, UserData> users) throws Exception {
        for (Interest row : objects) {
            if (!users.containsKey(row.id)) {
                throw new Exception("The user is not readed yet");
            }
            UserData user = users.get(row.id);
            user.sport = row.favouriteStport;
            users.put(user.id, user);
        }
        return users;
    }

    protected Map<Integer, UserData> persistAdresses(List<Availability> objects, Map<Integer, UserData> users) throws Exception {
        for (Availability row : objects) {
            if (!users.containsKey(row.id)) {
                throw new Exception("The user is not readed yet");
            }
            UserData user = users.get(row.id);
            user.email = row.email;
            user.address = row.address;
            users.put(user.id, user);
        }
        return users;
    }
    
    public void persistCSVData(List<User> rawusers, List<Interest> interests, List<Availability> availabilities, Model model) throws Exception{
        Map<Integer, UserData> users = persistAdresses(availabilities, persistInterests(interests, persistUsers(rawusers)));
        for (UserData user : users.values()) {
            model.insertUser(user.id, user.lastName, user.firstName, user.gender, user.age, user.email, user.address, user.sport);
        }
    }
}
