package no.ntnu.idatt2105.tokenauth.demo.dao;

import no.ntnu.idatt2105.tokenauth.demo.model.UserInfoResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a mock DAO for testing purposes.
 */
public class MockDao {

    private static List<UserInfoResponse> users = new ArrayList<UserInfoResponse>() {
        {
            add(new UserInfoResponse("Ole", "Nordmann", "M", "ole1", "password1"));
            add(new UserInfoResponse("Kari", "Grossmann", "F", "kari2", "password2"));
            add(new UserInfoResponse("John", "Smith", "M", "john3", "password3"));
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