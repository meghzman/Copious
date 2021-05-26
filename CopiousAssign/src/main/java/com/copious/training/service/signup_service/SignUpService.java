package com.copious.training.service.signup_service;

import com.copious.training.exception.category.UserNotFoundException;
import com.copious.training.exception.category.UserSignUpServiceException;
import com.copious.training.model.ErrorMessages;
import com.copious.training.model.Request.UserSignupRequestModel;
import com.copious.training.model.Response.UserSignUpResponseModel;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Log4j
@Service
public class SignUpService {

    public static List<UserSignupRequestModel> usersignup = new ArrayList<>();
    //static int count = 3;

    static {
        usersignup.add(new UserSignupRequestModel("11234", "meghdoot", "ojha", "8770116324", "indore"));
        usersignup.add(new UserSignupRequestModel("21231", "parth", "ojha", "9827644371", "pune"));
        usersignup.add(new UserSignupRequestModel("31232", "ketan", "ojha", "6263045245", "Chennai"));
    }

    private final Class<UserSignupRequestModel> modelClass = UserSignupRequestModel.class;
//user below method to generate random 12 digit id
/*    public static long generateRandomDigits() {
        long length = 12;
        String chars = "0123456789";
        String str = new Random().ints(length, 0, chars.length())
                .mapToObj(i -> "" + chars.charAt(i))
                .collect(Collectors.joining());
        long number = Long.parseLong(str);
        return number;
    }*/

    public UserSignUpResponseModel signUp(UserSignupRequestModel user) {

        for (UserSignupRequestModel find : usersignup) {
            if (find.getfName().equals(user.getfName()) && find.getlName().equals(user.getlName())) {
                log.error("User already exist with name: " + find.getfName() + find.getlName());
                throw new UserSignUpServiceException(ErrorMessages.Name_ALREADY_EXIST);
            }
        }

        for (UserSignupRequestModel find2 : usersignup) {
            if (find2.getMobileNumber().equals(user.getMobileNumber())) {
                log.error("User already exist with mobile: " + find2.getMobileNumber());
                throw new UserSignUpServiceException(ErrorMessages.Mobile_ALREADY_EXIST);
            }
        }

        for (UserSignupRequestModel find3 : usersignup) {
            if (find3.getId().equals(user.getId())) {
                log.error("User already exist with Id: " + find3.getId());
                throw new UserSignUpServiceException(ErrorMessages.Id_ALREADY_EXIST);
            }
        }
        /*if (user.getId() == null) {
            log.info("generating user id");
            //user.setId(++count);
            //user.setId(generateRandomDigits());
        }*/

        usersignup.add(user);
        log.info("user added");
        UserSignUpResponseModel res = new UserSignUpResponseModel(user.getId(), user.getfName(), user.getlName(), user.getMobileNumber(), user.getAddress());
        return res;
    }

    public List<UserSignupRequestModel> getalluser() {
        if (usersignup.isEmpty()) {
            throw new UserSignUpServiceException(ErrorMessages.NO_RECORDS_FOUND);
        }
        return usersignup;
    }

    public UserSignUpResponseModel getUserByMobile(String mobile) throws Exception {
        //serSignupRequestModel signupRequestModel = new UserSignupRequestModel();
        for (UserSignupRequestModel find : usersignup) {
            if (find.getMobileNumber().equals(mobile)) {
                System.out.println("User matched " + find.getfName());
                UserSignUpResponseModel res = new UserSignUpResponseModel(find.getId(), find.getfName(), find.getlName(), find.getMobileNumber(), find.getAddress());
                return res;
            } else throw new UserNotFoundException(ErrorMessages.NO_RECORDS_FOUND);
        }
        return null;
    }

    public UserSignUpResponseModel getUserById(String id) throws Exception {
        //serSignupRequestModel signupRequestModel = new UserSignupRequestModel();
        for (UserSignupRequestModel find : usersignup) {
            if (find.getId().equals(id)) {
                System.out.println("User matched " + find.getfName());
                UserSignUpResponseModel res = new UserSignUpResponseModel(find.getId(), find.getfName(), find.getlName(), find.getMobileNumber(), find.getAddress());
                return res;
            } else throw new UserNotFoundException(ErrorMessages.NO_RECORDS_FOUND);
        }
        return null;
    }

    public UserSignUpResponseModel getUserByfName(String fName) throws Exception {
        //serSignupRequestModel signupRequestModel = new UserSignupRequestModel();
        for (UserSignupRequestModel find : usersignup) {
            if (find.getfName().equals(fName)) {
                System.out.println("User matched " + find.getfName());
                UserSignUpResponseModel res = new UserSignUpResponseModel(find.getId(), find.getfName(), find.getlName(), find.getMobileNumber(), find.getAddress());
                return res;
            } else throw new UserNotFoundException(ErrorMessages.NO_RECORDS_FOUND);
        }
        return null;
    }

    public String deleteUser() {
        usersignup.removeAll(usersignup);
        return "All users deleted";
    }

    public UserSignUpResponseModel updateUser(String id, UserSignupRequestModel model) throws Exception{
        for (int i = 0; i < usersignup.size(); i++) {
            UserSignupRequestModel user = usersignup.get(i);
            if (user.getId().equals(id)) {
                usersignup.set(i, model);
                UserSignUpResponseModel res = new UserSignUpResponseModel(model.getId(), model.getfName(), model.getlName(), model.getMobileNumber(), model.getAddress());
                return res;
            }
            else throw new UserNotFoundException(ErrorMessages.NO_RECORDS_FOUND);

        }
        return null;
    }
}
