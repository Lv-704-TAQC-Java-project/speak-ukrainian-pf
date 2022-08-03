package com.ita.edu.speakua.ui.header;

import com.ita.edu.speakua.ui.BasePage;
import com.ita.edu.speakua.ui.header.profileMenuAdmin.AdminProfileMenuComponent;
import com.ita.edu.speakua.ui.header.profileMenuGuest.GuestProfileMenuComponent;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

public class HeaderComponent extends BasePage {

    static protected boolean authorizationIdentity = true;


    public HeaderComponent(WebDriver driver) {
        super(driver);
    }

    public HeaderComponent getHeader(){
        return new HeaderComponent(driver);
    }

    private AdminProfileMenuComponent getAdmin(){
        return new AdminProfileMenuComponent(driver);
    }

    private GuestProfileMenuComponent getGuest(){
        return new GuestProfileMenuComponent(driver);
    }

    public AbstractProfileMenu openProfileMenu(){
        List<AbstractProfileMenu> prof = new ArrayList<>();
        prof.add(new AdminProfileMenuComponent(driver));
        prof.add(new GuestProfileMenuComponent(driver));

        if (authorizationIdentity) return (AdminProfileMenuComponent)prof.get(0);
        return (GuestProfileMenuComponent)prof.get(1);
    }

}
