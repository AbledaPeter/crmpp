package crmpp.csvreader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import crmpp.Model;
import crmpp.User;
import crmpp.csvreader.entities.Availability;
import crmpp.csvreader.entities.Interest;

/**
 *
 * @author Devids
 */
public class PersistCSVElements {

    protected Map<Integer, User> persistUsers(List<crmpp.csvreader.entities.User> objects) throws Exception {
        HashMap<Integer, User> users = new HashMap<>();
        for (crmpp.csvreader.entities.User row : objects) {
            users.put(row.id, new User(row.id, row.firstName, row.lastName, row.gender, row.age, null, null, null));
        }
        return users;
    }

    protected Map<Integer, User> persistInterests(List<Interest> objects, Map<Integer, User> users) throws Exception {
        for (Interest row : objects) {
            if (!users.containsKey(row.id)) {
                throw new Exception("The user is not readed yet");
            }
            User user = users.get(row.id);
            user.sport = row.favouriteStport;
            users.put(user.id, user);
        }
        return users;
    }

    protected Map<Integer, User> persistAdresses(List<Availability> objects, Map<Integer, User> users) throws Exception {
        for (Availability row : objects) {
            if (!users.containsKey(row.id)) {
                throw new Exception("The user is not readed yet");
            }
            User user = users.get(row.id);
            user.email = row.email;
            user.address = row.address;
            users.put(user.id, user);
        }
        return users;
    }
    
    public void persistCSVData(List<crmpp.csvreader.entities.User> rawusers, List<Interest> interests, List<Availability> availabilities, Model model) throws Exception{
        Map<Integer, User> users = persistAdresses(availabilities, persistInterests(interests, persistUsers(rawusers)));
        for (User user : users.values()) {
            model.insertUser(user.id, user.lastName, user.firstName, user.gender, user.age, user.email, user.address, user.sport);
        }
    }
}
