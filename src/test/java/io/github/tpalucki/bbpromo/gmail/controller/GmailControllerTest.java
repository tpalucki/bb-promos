package io.github.tpalucki.bbpromo.gmail.controller;

import org.junit.jupiter.api.Test;

import javax.mail.*;
import javax.mail.search.SearchTerm;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;


class GmailControllerTest {


//    ##### Mail Config #####
//    spring.mail.host=smtp.gmail.com
//    spring.mail.port=587
//    spring.mail.username=tpalucki@gmail.com
//    spring.mail.password=<login password to smtp server>
//    spring.mail.properties.mail.smtp.auth=true
//    spring.mail.properties.mail.smtp.starttls.enable=true
//    spring.mail.properties.mail.smtp.starttls.required=true
//    spring.mail.properties.mail.smtp.connectiontimeout=5000
//    spring.mail.properties.mail.smtp.timeout=5000
//    spring.mail.properties.mail.smtp.writetimeout=5000

    @Test
    void downloaFromGmail() throws IOException {
        String host = "smtp.gmail.com";
        String port = "587";
        String userName = "tpalucki@gmail.com";
        String password = "";

        String keyword = "obserwowanym";

        Properties properties = new Properties();

        // server setting
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.user", userName);
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.starttls.enable", true);
        properties.put("mail.smtp.starttls.required", true);
        properties.put("mail.smtp.connectiontimeout", 5000);
        properties.put("mail.smtp.timeout", 5000);

        int imapPort = 993;
        properties.put("mail.imap.host", "imap.gmail.com");
        properties.put("mail.imap.port", imapPort);
        properties.put("mail.imap.user", userName);
        properties.put("mail.imap.connectiontimeout", 5000);
        properties.put("mail.imap.starttls.enable", true);
        properties.put("mail.imap.starttls.required", true);


        // SSL setting
        properties.setProperty("mail.imap.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        properties.setProperty("mail.smtp.socketFactory.fallback", "false");
        properties.setProperty("mail.smtp.socketFactory.port",
                String.valueOf(imapPort));

        Session session = Session.getDefaultInstance(properties);

        try {
            // connects to the message store
            Store store = session.getStore("imap");
            store.connect(userName, password);

            // opens the inbox folder
            Folder folderInbox = store.getFolder("INBOX");
            folderInbox.open(Folder.READ_ONLY);

            // creates a search criterion
            SearchTerm searchCondition = new SearchTerm() {
                @Override
                public boolean match(Message message) {
                    try {
                        for (Address from: message.getFrom()) {
                            if (from.toString().contains("allegro")) {
                                return true;
                            }
                        }

//                        if (message.getSubject().contains(keyword)) {
//                            return true;
//                        }
                    } catch (MessagingException ex) {
                        ex.printStackTrace();
                    }
                    return false;
                }
            };

            // performs search through the folder
            Message[] foundMessages = folderInbox.search(searchCondition);

            for (int i = 0; i < foundMessages.length; i++) {
                Message message = foundMessages[i];
                printMessage(message);
            }

            // disconnect
            folderInbox.close(false);
            store.close();
        } catch (NoSuchProviderException ex) {
            System.out.println("No provider.");
            ex.printStackTrace();
        } catch (MessagingException ex) {
            System.out.println("Could not connect to the message store.");
            ex.printStackTrace();
        }
    }

    private void printMessage(Message message) throws MessagingException, IOException {
        final Address[] from = message.getFrom();
        final String subject = message.getSubject();
        final Enumeration<Header> allHeaders = message.getAllHeaders();
        final Folder folder = message.getFolder();
        final Flags flags = message.getFlags();
        final Address[] allRecipients = message.getAllRecipients();
        final String content = message.getContent().toString();
        System.out.println("---------------------------------");
        System.out.println("Subject: " + subject);
        System.out.println("From: " + from[0]);
        System.out.println("Text: " + content);
    }

}