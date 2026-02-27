package com.example.lunar.listener;

import com.example.lunar.dto.TransferCompletedEvent;
import com.example.lunar.helper.MailHelper;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Log4j2
@Component
public class TransferEventListener {

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleTransferCompletedEvent(TransferCompletedEvent event) {
        log.info("Received TransferCompletedEvent for transaction {}. Sending email...", event.transactionId());

        // TODO: Get real email address from user profile, hardcoded for testing
        String toEmail = "nvtuanq123@gmail.com";
        String subject = "Transfer Confirmation #" + event.transactionId();
        String htmlBody = String.format(
                "<h1>Transfer Successful</h1><p>You have successfully transferred %s to wallet %s.</p>",
                event.amount(), event.toWalletId());

        try {
            MailHelper.sendMail(toEmail, subject, htmlBody);
        } catch (Exception e) {
            // Log and swallow exception so it doesn't affect other async tasks,
            // though @Async already swallows them unless configured otherwise.
            log.error("Failed to send transfer confirmation email for transaction {}", event.transactionId(), e);
        }
    }
}
