package data_access;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import entity.User;
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

public class EditInfoDataAccessObjectTest {

    private EditInfoDataAccessObject editDAO;

    @BeforeEach
    void setUp() {
        editDAO = new EditInfoDataAccessObject();
        editDAO.setcsvPathAndcsvFile("src/test/test_resources/test_userDatabase.csv");
    }

    @Test
    void processLocation(){

    }

    @Test
    void setCurrUserandChanges(){

    }

    @Test
    void setcsvPathAndcsvFile(){

    }

    @Test
    void editUsername(){

    }


    @Test
    void editPasswordOrLocation(){

    }



}
