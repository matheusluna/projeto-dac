package io.github.dac.rhecruta.javamail.jms.reveicers;

import io.github.dac.rhecruta.javamail.EmailService;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.json.Json;
import javax.json.JsonObject;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;

@MessageDriven(
        activationConfig = {
                @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
                @ActivationConfigProperty(propertyName = "destination", propertyValue = "queue"),
                @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:global/jms/email"),
        }
)
public class EmailReveicer implements MessageListener {

    @EJB
    private EmailService emailService;

    @Override
    public void onMessage(Message message) {

        try {
            String body = message.getBody(String.class);


            JsonObject jsonEmail = Json
                    .createReader(
                            new StringReader(body)
                    )
                    .readObject();

            emailService.sendEmail(
                    jsonEmail.getString("destinatario"),
                    jsonEmail.getString("titulo"),
                    jsonEmail.getString("corpo")
            );

        } catch (Exception ex) {
            Logger.getLogger(EmailReveicer.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }

    }
}
