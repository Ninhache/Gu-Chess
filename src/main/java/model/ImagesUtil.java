package model;

import javafx.scene.image.Image;

import java.net.URL;

public class ImagesUtil {

    public static Image createImage(Object context, String resourceName) {
        URL _url = context.getClass().getResource(resourceName);

        if (_url != null) {
            return new Image(_url.toExternalForm());
        } else {
            return null;
        }

    }

}
