package br.com.gd.picpay.services;

import br.com.gd.picpay.entities.OtpEntity;

public interface OTPService {

   int generateOtpCode(String email);
   void saveOtp(String email, int code);
   OtpEntity findCodeByEmail(String email);
   void remove(Long id);
}
