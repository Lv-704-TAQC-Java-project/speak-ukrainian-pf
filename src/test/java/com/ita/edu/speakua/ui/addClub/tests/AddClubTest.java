package com.ita.edu.speakua.ui.addClub.tests;

import com.ita.edu.speakua.ui.HomePage;
import com.ita.edu.speakua.ui.header.profileMenuAdmin.addLocation.Location;
import com.ita.edu.speakua.ui.header.profileMenuAdmin.profilePage.ProfilePage;
import com.ita.edu.speakua.ui.runners.LoginTestRunner;
import io.qameta.allure.Issue;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddClubTest extends LoginTestRunner {
    private final String pathToImage = Paths.get(Paths.get(System.getProperty("user.dir")).toString(),
            "src", "test", "resources", "image.png").toString();

    private final String randomData = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

    @DataProvider(name = "cityFromDropDownAddLocationMenu")
    public Object[][] cityFromDropDownAddLocationMenu() {
        return new Object[][]{
                {"Спортивні секції", new Location(randomData,
                        "Київ", "Деснянський", "вул. Садова, 1а", "50.4485253, 30.4735083", "0632233456"),
                        "a@gdg.ooo", "0632233456",
                },
                {"Вчіться, діти", new Location(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()),
                        "Харків", "Холодногірський", "вул. Садова, 1а", "50.4485253, 30.4735083", "0632233456"),
                        "dnjwp@kjs.ldo", "0632233456"

                }
        };
    }

    @Issue("TUA-237")
    @Test(dataProvider = "cityFromDropDownAddLocationMenu")
    public void addClub(String sections, Location location, String email, String phoneNumber) {
        ProfilePage profilePage = new HomePage(driver)
                .openAdminProfileMenu()
                .openUserProfilePage()
                .openAddClubModal()
                .inputNameOfClub(randomData)
                .selectCategoryClub(sections)
                .inputAgeFrom(4)
                .inputAgeTo(8)
                .openNextStep()
                .addLocation(location)
                .inputFaceBook(randomData)
                .inputBasicContact(randomData)
                .inputEmail(email)
                .inputSkype(randomData)
                .inputWhatsApp(randomData)
                .inputPhoneNumber(phoneNumber)
                .openNextStep()
                .addLogo(pathToImage)
                .addBackground(pathToImage)
                .addGallery(pathToImage)
                .inputDescription(new String(new char[70]).replace("\0", randomData))
                .addClub();

        Assert.assertTrue(profilePage.isEditProfileButtonVisible());
        Assert.assertTrue(profilePage.isAddButtonVisible());
    }
}
