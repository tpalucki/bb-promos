package io.github.tpalucki.bbpromo.gmail.controller;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import javax.mail.*;
import javax.mail.search.SearchTerm;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

@Slf4j
class GmailControllerTest {

    @Test
    @Disabled("Exploratory test to check gmail inbox features offered")
    void downloaFromGmail() throws IOException {
        String userName = "tpalucki@gmail.com";
        String password = "";

        String keyword = "allegro";

        Properties properties = new Properties();

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
                        for (Address from : message.getFrom()) {
                            if (from.toString().contains(keyword)) {
                                return true;
                            }
                        }
                    } catch (MessagingException ex) {
                        ex.printStackTrace();
                    }
                    return false;
                }
            };

            // performs search through the folder
            Message[] foundMessages = folderInbox.search(searchCondition);

            for (Message message : foundMessages) {
                printMessage(message);
                messageProcessing(message);
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

    @SneakyThrows
    private void messageProcessing(Message message) {
        final Document document = Jsoup.parseBodyFragment(message.getContent().toString());
        final Element body = document.body();

        log.info("Title: {}", document.title());
        // TODO document parse using JSOUP - you can retrieve the price/new set elements
    }

    private void printMessage(Message message) throws MessagingException, IOException {
        final Address[] from = message.getFrom();
        final String subject = message.getSubject();
        final Enumeration<Header> allHeaders = message.getAllHeaders();
        final Folder folder = message.getFolder();
        final Flags flags = message.getFlags();
        final Address[] allRecipients = message.getAllRecipients();
        final String content = message.getContent().toString();
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("Subject: " + subject);
        System.out.println("From: " + from[0]);
//        System.out.println("Folder: " + folder);
//        System.out.println("allHeaders = " + allHeaders);
//        System.out.println("flags = " + flags);
//        System.out.println("allRecipients = " + Arrays.toString(allRecipients));
        System.out.println("Content: " + content);
    }

}