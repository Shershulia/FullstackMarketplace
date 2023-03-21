package no.ntnu.fullstack.marketplace.dao;

import no.ntnu.fullstack.marketplace.model.UserInfoResponse;
import no.ntnu.fullstack.marketplace.dao.AbsctractDao;

import java.util.ArrayList;
import java.util.List;

//database connection with spring boot and h2 database


public class AccountDao extends AbsctractDao<UserInfoResponse> {


    public AccountDao() {
        setClazz(UserInfoResponse.class);
    }

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