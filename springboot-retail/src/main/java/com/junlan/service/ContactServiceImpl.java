package com.junlan.service;

import com.junlan.domain.ContactEntity;
import com.junlan.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl implements ContactServiceInt {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private JavaMailSender javaMailService;

    public void saveContactUser(ContactEntity contact) {

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(contact.getEmail());
        mailMessage.setSubject("Contact Us!!");
        mailMessage.setText("Hello " + contact.getName() + "\n" + contact.getMessage());
        javaMailService.send(mailMessage);
        contactRepository.save(contact);
    }
}
