package crmpp.csvreader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import crmpp.Model;
import crmpp.User;

/**
 *
 * @author Devids
 */
public class PersistCSVElements {

    protected Map<Integer, User> persistUsers(List<Object[]> objects) throws Exception {
        HashMap<Integer, User> users = new HashMap<>();
        for (Object[] row : objects) {
            if (!(row[0] instanceof Integer)) {
                throw new Exception("First element must be an Integer");
            }
            if (!(row[1] instanceof String)) {
                throw new Exception("Second element must be a String");
            }
            if (!(row[2] instanceof String)) {
                throw new Exception("Third element must be a String");
            }
            if (!(row[3] instanceof String)) {
                throw new Exception("Fourth element must be a String");
            }
            if (!(row[0] instanceof Integer)) {
                throw new Exception("Fifth element must be an Integer");
            }
            users.put((Integer) row[0], new User((int) row[0], (String) row[1], (String) row[2], (String) row[3], (int) row[4], null, null, null));
        }
        return users;
    }

    protected Map<Integer, User> persistInterests(List<Object[]> objects, Map<Integer, User> users) throws Exception {
        for (Object[] row : objects) {
            if (!(row[0] instanceof Integer)) {
                throw new Exception("First element must be an Integer");
            }
            if (!(row[1] instanceof String)) {
                throw new Exception("Second element must be a String");
            }
            if (!users.containsKey(row[0])) {
                throw new Exception("The user is not readed yet");
            }
            User user = users.get(row[0]);
            user.sport = (String) row[1];
            users.put(user.id, user);
        }
        return users;
    }

    protected Map<Integer, User> persistAdresses(List<Object[]> objects, Map<Integer, User> users) throws Exception {
        for (Object[] row : objects) {
            if (!(row[0] instanceof Integer)) {
                throw new Exception("First element must be an Integer");
            }
            if (!(row[1] instanceof String)) {
                throw new Exception("Second element must be a String");
            }
            if (!(row[2] instanceof String)) {
                throw new Exception("Third element must be a String");
            }
            if (!users.containsKey(row[0])) {
                throw new Exception("The user is not readed yet");
            }
            User user = users.get(row[0]);
            user.email = (String) row[1];
            user.address = (String) row[2];
            users.put(user.id, user);
        }
        return users;
    }
    
    public void persistCSVData(List<Object[]> rawusers, List<Object[]> interests, List<Object[]> availabilities, Model model) throws Exception{
        Map<Integer, User> users = persistAdresses(availabilities, persistInterests(interests, persistUsers(rawusers)));
        for (User user : users.values()) {
            model.insertUser(user.id, user.lastName, user.firstName, user.gender, user.age, user.email, user.address, user.sport);
        }
    }
}
