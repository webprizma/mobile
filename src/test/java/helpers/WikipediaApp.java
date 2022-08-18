package helpers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import static org.apache.commons.io.FileUtils.copyInputStreamToFile;

public class WikipediaApp {
    public static File getApp() {
        String appUrl = "https://github.com/wikimedia/apps-android-wikipedia/" +
                "releases/download/latest/app-alpha-universal-release.apk";
        String appPath = "src/test/resources/apps/app-alpha-universal-release.apk";

        File app = new File(appPath);

        if(!app.exists()) {
            try (InputStream in = new URL(appUrl).openStream()) {
                copyInputStreamToFile(in, app);
            }
            catch (IOException e) {
                throw new AssertionError("Failed to download application", e);
            }
        }
        return app;
    }

    public static String getAppPackage() {
        return "org.wikipedia.alpha";
    }

    public  static String getAppActivity() {
        return "org.wikipedia.main.MainActivity";
    }
}
