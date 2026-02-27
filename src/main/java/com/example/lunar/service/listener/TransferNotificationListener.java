package com.example.lunar.service.listener;

import com.example.lunar.helper.MailHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class TransferNotificationListener {

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleTransferCompleted(TransactionPhase event) {

        MailHelper.sendMail("nvtuanq123@gmail.com", "Test send mail", "<h1>Hello test send mail nha</h1>");
    }
}
