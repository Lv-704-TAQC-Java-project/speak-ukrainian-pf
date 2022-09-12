package com.ita.edu.speakua.ui.addClub.tests;

import com.ita.edu.speakua.ui.HomePage;
import com.ita.edu.speakua.ui.header.profileMenuAdmin.addLocationModal.Location;
import com.ita.edu.speakua.ui.header.profileMenuAdmin.profilePage.ProfilePage;
import com.ita.edu.speakua.ui.runners.LoginTestRunner;
import com.ita.edu.speakua.utils.jdbc.entity.LocationEntity;
import com.ita.edu.speakua.utils.jdbc.services.LocationService;
import io.qameta.allure.Issue;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AddClubTest extends LoginTestRunner {
    private final String pathToImage = Paths.get(Paths.get(System.getProperty("user.dir")).toString(),
            "src", "test", "resources", "image.png").toString();

    private final String randomData = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

    @DataProvider(name = "cityFromDropDownAddLocationMenu")
    public Object[][] cityFromDropDownAddLocationMenu() {
        return new Object[][]{
                {"Спортивні секції", new Location("Location " + randomData,
                        "Київ", "Деснянський", "вул. Садова, 1а", "50.4485253, 30.4735083", "0632233456"),
                        "a@gdg.ooo", "0632233456",
                }
        };
    }

    @Issue("TUA-237")
    @Test(dataProvider = "cityFromDropDownAddLocationMenu")
    public void addClub(String sections, Location location, String email, String phoneNumber) {
        String title = "Test " + randomData;
        String description = new String(new char[50]).replace("\0", randomData);

        ProfilePage profilePage = new HomePage(driver)
                .openAdminProfileMenu()
                .openUserProfilePage()
                .openAddClubModal()
                .inputNameOfClub(title)
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
                .enterDescription(description)
                .addClub();

        Assert.assertTrue(profilePage.isClubAvailable(title, description));

        String locationName = "Location " + randomData;
        LocationService locationService = new LocationService();
        List<LocationEntity> locations = locationService.getLocationByName(locationName);
        Assert.assertEquals(locations.size(), 1);
        Assert.assertEquals(locations.get(0).getName(), locationName);
    }
}
