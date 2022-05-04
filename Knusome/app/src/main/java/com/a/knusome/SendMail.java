package com.a.knusome;

import android.content.Context;
import android.widget.Toast;

import javax.mail.MessagingException;
import javax.mail.SendFailedException;

import androidx.appcompat.app.AppCompatActivity;


public class SendMail extends AppCompatActivity {
    String user = "jayangie4608@gmail.com"; // 보내는 계정의
    String password = "Wogus7391585!"; // 보내는 계정의 pw

    public void sendSecurityCode(Context context, String sendTo) {
        try {
            GMailSender gMailSender = new GMailSender(user, password);
            gMailSender.sendMail("KNUSOME 인증번호 입니다.", "158760을 입력하세요", sendTo);
            Toast.makeText(context, "이메일을 성공적으로 보냈습니다.", Toast.LENGTH_SHORT).show(); }

        catch (SendFailedException e) {
            Toast.makeText(context, "이메일 형식이 잘못되었습니다.", Toast.LENGTH_SHORT).show(); }

        catch (MessagingException e) {
            Toast.makeText(context, "이메일을 입력하세요", Toast.LENGTH_SHORT).show(); }

        catch (Exception e) {
            e.printStackTrace(); }
    }
}
