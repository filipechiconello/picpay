package br.com.gd.picpay.services;


import br.com.gd.picpay.dtos.requests.EmailRequestDTO;

public interface NotificationService {

    void notificationEmail(EmailRequestDTO emailRequestDTO);
}
