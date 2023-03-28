package br.com.gd.picpay.facades.impl;

import br.com.gd.picpay.dtos.requests.EmailRequestDTO;
import br.com.gd.picpay.entities.OtpEntity;
import br.com.gd.picpay.facades.OtpFacade;
import br.com.gd.picpay.services.NotificationService;
import br.com.gd.picpay.services.OTPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OtpFacadeImpl implements OtpFacade {

    @Autowired
    private OTPService otpService;

    @Autowired
    private NotificationService notificationService;

    @Value("${email.from}")
    private String emailFrom;

    @Value("${email.subject.otp}")
    private String emailSubject;

    @Override
    public void generateOtpCode(String email) {
        OtpEntity otp = otpService.findCodeByEmail(email);
        if (otp != null) {
            otpService.remove(otp.getId());
        }

        List<String> emailTo = new ArrayList<>();
        emailTo.add(email);

        EmailRequestDTO emailRequestDTO = new EmailRequestDTO();
        emailRequestDTO.setTo(emailTo);
        emailRequestDTO.setFrom(emailFrom);
        emailRequestDTO.setSubject(emailSubject);
        emailRequestDTO.setContent(String.valueOf(otpService.generateOtpCode(email)));

        notificationService.notificationEmail(emailRequestDTO);
    }
}
