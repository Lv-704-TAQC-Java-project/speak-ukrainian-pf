package com.ita.edu.speakua.ui.addGroup.tests;

import com.ita.edu.speakua.ui.HomePage;
import com.ita.edu.speakua.ui.header.profileMenuAdmin.addClubComponent.AddClubDescribeComponent;
import com.ita.edu.speakua.ui.runners.BaseTestRunnerWithLogIn;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddGroupComponentTest extends BaseTestRunnerWithLogIn {

    @Test
    public void verifyAddClubDescribeFieldValidDataTest(){
        AddClubDescribeComponent addClubDescribeComponent = new HomePage(driver)
                .openAdminProfileMenu()
                .openAddGroupModal()
                .inputNameOfClub("Спортивні танці")
                .chooseCategoryClub("Спортивні секції")
                .inputAgeFrom(4)
                .inputAgeTo(8)
                .clickNextStep()
                .inputPhoneNumber("0672131246")
                .clickNextStep()
                .inputDescribe("Lorem Ipsum is simply dummy text of the ");
        boolean verifyBtnFinishIsEnableFortyChars = addClubDescribeComponent.finishBtnIsEnable();

        addClubDescribeComponent
                .clearDescribeField()
                .inputDescribe("Lorem Ipsum is simply dummy text of the printing and typesetting industry. " +
                        "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, " +
                        "when an unknown printer took a galley of type and scrambled it to make a type specimen book. " +
                        "It has survived not only five centuries, but also the leap into electronic typesetting, " +
                        "remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset " +
                        "sheets containing Lorem Ipsum passages, and more recently with desktop publishing software " +
                        "like Aldus PageMaker including versions of Lorem Ipsum. It is a long established fact that " +
                        "a reader will be distracted by the readable content of a page when looking at its layout. " +
                        "The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, " +
                        "as opposed to using 'Content here, content here', making it look like readable English. " +
                        "Many desktop publishing packages and web page editors now use Lorem Ipsum as their default " +
                        "model text, and a search for");
        boolean verifyBtnFinishIsEnableThousandChars = addClubDescribeComponent.finishBtnIsEnable();

        addClubDescribeComponent
                .clearDescribeField()
                .inputDescribe("Lorem Ipsum is simply dummy text of the printing and typesetting industry. " +
                        "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, " +
                        "when an unknown printer took a galley of type and scrambled it to make a type specimen book. " +
                        "It has survived not only five centuries, but also the leap into electronic typesetting, " +
                        "remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset " +
                        "sheets containing Lorem Ipsum passages, and more recently with desktop publishing software " +
                        "like Aldus PageMaker including versions of Lorem Ipsum. It is a long established fact that " +
                        "a reader will be distracted by the readable content of a page when looking at its layout. " +
                        "The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, " +
                        "as opposed to using 'Content here, content here', making it look like readable English. " +
                        "Many desktop publishing packages and web page editors now use Lorem Ipsum as their default " +
                        "model text, and a search for Many desktop publishing packages and web page editors now " +
                        "use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover " +
                        "many web sites still in their infancy. Various versions have evolved over the years, " +
                        "sometimes by accident, sometimes on purpose (injected humour and the like) Contrary to " +
                        "popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical " +
                        "Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin 1");
        boolean verifyBtnFinishIsEnableOneAndHalfThousandChars = addClubDescribeComponent.finishBtnIsEnable();

        Assert.assertTrue(verifyBtnFinishIsEnableFortyChars);
        Assert.assertTrue(verifyBtnFinishIsEnableThousandChars);
        Assert.assertTrue(verifyBtnFinishIsEnableOneAndHalfThousandChars);
    }
}
