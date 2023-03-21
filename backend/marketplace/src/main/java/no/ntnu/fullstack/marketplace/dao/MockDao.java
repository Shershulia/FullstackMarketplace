package no.ntnu.fullstack.marketplace.dao;

import no.ntnu.fullstack.marketplace.model.UserInfoResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a mock DAO for testing purposes.
 */
public class MockDao {

    private static List<UserInfoResponse> users = new ArrayList<UserInfoResponse>() {
        {
            add(new UserInfoResponse("Ole", "Nordmann", "M", 67,1,"ole1", "password1"));
            add(new UserInfoResponse("Kari", "Grossmann", "F", 53,2 ,"kari2", "password2"));
            add(new UserInfoResponse("John", "Smith", "M", null,3,"john3", "password3"));
        }
    };

    public static boolean checkUserCredentials(final String username, final String password) {
        for(UserInfoResponse user : users){
            if(user.getUsername().equals(username) && user.getPassword().equals(password))  {
                return true;
            }
        }
        return false;
    }

    public static UserInfoResponse getUser(final String userId){
        for(UserInfoResponse user : users){
            if(user.getUsername().equals(userId)){
                return user;
            }
        }
        return null;
    }

}