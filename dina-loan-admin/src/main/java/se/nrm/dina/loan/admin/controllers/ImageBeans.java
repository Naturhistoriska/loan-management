package se.nrm.dina.loan.admin.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author idali
 */
@Named("imageBeans")
@SessionScoped
public class ImageBeans implements Serializable {
  
  private final List<String> images;

  public ImageBeans() {

    images = new ArrayList<>();
    images.add("/resources/images/randomImages/IMG_3150.JPG");
    images.add("/resources/images/randomImages/IMG_3155.JPG");
    images.add("/resources/images/randomImages/IMG_3163.JPG");
    images.add("/resources/images/randomImages/clytra.jpg");
    images.add("/resources/images/randomImages/faglar.jpg");
    images.add("/resources/images/randomImages/grashoppor.jpg");
    images.add("/resources/images/randomImages/machaon.jpg");
    images.add("/resources/images/randomImages/smaskrake.jpg");
    images.add("/resources/images/randomImages/sommargylling_blakraka.jpg");
    images.add("/resources/images/randomImages/tNRM50926.jpg");
    images.add("/resources/images/randomImages/tNRM52466b.jpg");
    images.add("/resources/images/randomImages/tNRM55280_T4912.jpg");
    images.add("/resources/images/randomImages/vattenrall.jpg");
  }

  public String getImage() {
    return images.get(getRandomNumber());
  }

  private int getRandomNumber() {
    int low = 0;
    int high = 12;
    return (int) (Math.random() * (high - low)) + low;
  }

  public List<String> getImages() {
    return images;
  }
}
