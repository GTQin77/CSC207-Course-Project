package use_case.edit_info;

import data_access.EditInfoDataAccessObject;
import interface_adapter.EditInfo.EditInfoPresenter;
import interface_adapter.EditInfo.EditInfoViewModel;
import interface_adapter.ViewManagerModel;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import entity.User;
import org.mockito.Mockito;
import services.UserService;

import java.nio.file.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class EditInfoInteractorTest {

    private static EditInfoInteractor editInteractor;
    private static EditInfoDataAccessObject editDAO;
    private static UserService userService;
    private static EditInfoInputData input;
    private static EditInfoPresenter editPresenter;

    @BeforeAll
    static void setUp() {
        ArrayList<Double> location = new ArrayList<Double>();
        location.add(1.23);
        location.add(4.56);
        User testUser = new User("martha", "caldwell", location);

        userService = Mockito.mock(UserService.class);
        userService.setCurrentUser(testUser);

        // Create an input data instance
        input = new EditInfoInputData("megan", "12345", "12345",
                "1.55,4.11", testUser);


        editDAO = new EditInfoDataAccessObject();
        editDAO.setcsvPathAndcsvFile("src/test/test_resources/test_database.csv");

        ViewManagerModel vmModel = new ViewManagerModel();
        EditInfoViewModel vm = new EditInfoViewModel();

        editPresenter = new EditInfoPresenter(vmModel, vm);

        editInteractor = new EditInfoInteractor(editDAO, editPresenter, testUser);
    }





    @AfterEach
    void tearDown() {
    }
}