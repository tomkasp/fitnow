package com.tomkasp.profile.webui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Tomasz Kasprzycki (A042191)
 */
@RestController
@RequestMapping(value = "/api")
public class FacebookResource {

    private final Facebook facebook;
    private final ConnectionRepository connectionRepository;

    @Autowired
    public FacebookResource(Facebook facebook, ConnectionRepository connectionRepository) {
        this.facebook = facebook;
        this.connectionRepository = connectionRepository;
    }

    @RequestMapping(value = "/facebookdata", method = RequestMethod.GET)
    public String getAll() throws IOException {
        InputStream in = new ByteArrayInputStream(facebook.userOperations().getUserProfileImage());
        BufferedImage bImageFromConvert = ImageIO.read(in);

        ImageIO.write(bImageFromConvert, "jpg", new File(
            "c:/new-darksouls.jpg"));
        return facebook.userOperations().getUserProfile().toString();
    }
}
