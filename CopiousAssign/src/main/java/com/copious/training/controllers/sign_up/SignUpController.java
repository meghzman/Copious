package com.copious.training.controllers.sign_up;

import com.copious.training.exception.GenericResponse;
import com.copious.training.exception.category.UserNotFoundException;
import com.copious.training.exception.category.UserSignUpServiceException;
import com.copious.training.model.ErrorMessages;
import com.copious.training.model.Request.UserSignupRequestModel;
import com.copious.training.model.Response.UserSignUpResponseModel;
import com.copious.training.service.signup_service.SignUpService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.copious.training.service.signup_service.SignUpService.usersignup;

@RestController
@RequestMapping(value = "/user")
@Api(value = "User Controller")
public class SignUpController {

    @Autowired
    private SignUpService signUpService;

    @PostMapping(value = "/signup")
    public ResponseEntity<GenericResponse<UserSignUpResponseModel>> signupModel(@Valid @RequestBody UserSignupRequestModel signupModel) throws Exception {

        return new ResponseEntity<>(
                new GenericResponse<>(true, HttpStatus.OK.name(), signUpService.signUp(signupModel)), HttpStatus.OK);

    }

    @ApiOperation(value = "Get all user")
    @GetMapping(value = "/getall")
    public ResponseEntity<GenericResponse<List<UserSignupRequestModel>>> getAllUsers() throws Exception {

        return new ResponseEntity<>(new GenericResponse<>(true, HttpStatus.OK.name(), signUpService.getalluser()), HttpStatus.OK);
    }

    @ApiOperation(value = "Get single user by mobile")
    @GetMapping(value = "/get/mobile/{mobile}")
    public ResponseEntity<GenericResponse<UserSignUpResponseModel>> getSingleUserBymobile(@Valid @PathVariable String mobile) throws Exception {
        if (mobile == null) {
            throw new UserSignUpServiceException(ErrorMessages.NO_RECORDS_FOUND);
        }
        return new ResponseEntity<>(new GenericResponse<>(true, HttpStatus.OK.name(), signUpService.getUserByMobile(mobile)), HttpStatus.OK);
    }

    @ApiOperation(value = "Get single user by fName")
    @GetMapping(value = "/get/fname/{fName}")
    public ResponseEntity<GenericResponse<UserSignUpResponseModel>> getSingleUserByfName(@Valid @PathVariable String fName) throws Exception {
        if (fName == null) {
            throw new UserSignUpServiceException(ErrorMessages.NO_RECORDS_FOUND);
        }
        return new ResponseEntity<>(new GenericResponse<>(true, HttpStatus.OK.name(), signUpService.getUserByfName(fName)), HttpStatus.OK);
    }

    @ApiOperation(value = "Get single user by id")
    @GetMapping(value = "/get/id/{id}")
    public ResponseEntity<GenericResponse<UserSignUpResponseModel>> getSingleUserByid(@Valid @PathVariable String id) throws Exception {
        if (id == null) {
            throw new UserSignUpServiceException(ErrorMessages.NO_RECORDS_FOUND);
        }
        return new ResponseEntity<>(new GenericResponse<>(true, HttpStatus.OK.name(), signUpService.getUserById(id)), HttpStatus.OK);
    }

    @ApiOperation(value = "Delete all user")
    @DeleteMapping(value = "/deleteAll")
    public ResponseEntity<GenericResponse<Object>> deleteUser() {

        if (usersignup.isEmpty()) {
            throw new UserNotFoundException(ErrorMessages.NO_RECORDS_FOUND);
        }
        return new ResponseEntity<>(new GenericResponse<>(true, HttpStatus.OK.name(), signUpService.deleteUser()), HttpStatus.OK);
    }

    @ApiOperation(value = "Update user")
    @PutMapping(value = "/update/{id}")
    public ResponseEntity<GenericResponse<UserSignUpResponseModel>> updateUser(@Valid @RequestBody UserSignupRequestModel model, @PathVariable String id) throws Exception {

       // signUpService.updateUser(id, model);
        return new ResponseEntity<>(new GenericResponse<>(true, HttpStatus.OK.name(), signUpService.updateUser(id,model)), HttpStatus.OK);
    }

}

