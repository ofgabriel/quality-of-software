package com.ufrj.pp.delegates;

import java.util.Locale;
import com.ufrj.pp.service.MailService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import com.ufrj.pp.service.dto.*;

@Component
public class StatusUpdateEmailDelegate implements JavaDelegate {

    @Autowired
    MailService mailService;

    @Autowired
    SpringTemplateEngine templateEngine;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        SuitProcessDTO suitProcess = (SuitProcessDTO) delegateExecution.getVariable("processInstance");
        String to = "xxx@gmail.com";
        String subject = String.format("[Causa] Lembrete para causa %s", suitProcess.getSuit().getSuitNumber());
        Context context = new Context(Locale.getDefault());
        String content = templateEngine.process("mail/statusUpdateEmail", context);
        mailService.sendEmail(to, subject, content, false, true);
    }
}
