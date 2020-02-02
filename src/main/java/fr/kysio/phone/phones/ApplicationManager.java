package fr.kysio.phone.phones;

import fr.kysio.phone.phones.applications.MainMenuApp;
import fr.kysio.phone.phones.applications.UnlockApp;
import net.minecraftforge.common.MinecraftForge;

import java.util.ArrayList;

public class ApplicationManager {

    private static ArrayList<Application> applications = new ArrayList<>();

    private static boolean phoneOn;

    public static Application currentApp;

    public static boolean isPhoneOn() {
        return phoneOn;
    }

    public static void setPhoneOn(boolean phone) {
        phoneOn = phone;
        if (phone) {
            launchApplication(mainApp());
        } else {
            for (Application application : applications) {
                application.setEnabled(false);
                currentApp = null;
            }
        }
    }

    public static void launchApplication(String name) {
        if (currentApp != null) {
            System.out.println("CURRENT APP : "+currentApp);
            currentApp.setEnabled(false);
        }
        for (Application application : applications) {
            if (application.getName().equals(name)) {
                application.setEnabled(true);
                currentApp = application;
            }
        }

        if (currentApp == null || !currentApp.getName().equals(name)) {
            System.out.println("[ERROR] Error when launching app " + name + ". Please verify if the app is registred.");
        }
    }

    public static String mainApp() {
        return "unlock";
    }

    public static void addApp(Application application) {
        if (applications.contains(application)) return;
        applications.add(application);
    }

    public static void removeApp(Application application) {
        if (!applications.contains(application)) return;
        applications.remove(application);
    }

    public static void loadApplications() {
        applications.add(new UnlockApp());
        applications.add(new MainMenuApp());

        for (Application application : applications) {
            //application.setEnabled(false);
            MinecraftForge.EVENT_BUS.register(application);
        }
    }

}
