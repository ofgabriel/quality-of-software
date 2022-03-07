package com.ufrj.pp.delegates;

import java.util.Locale;
import java.util.List;
import com.ufrj.pp.service.MailService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import com.ufrj.pp.repository.*;
import com.ufrj.pp.domain.*;
import org.springframework.data.domain.Example;

@Component
public class VerifySuitExistenceDelegate implements JavaDelegate {

    @Autowired
    SuitRepository suitRepository;

    @Autowired
    SuitProcessRepository suitProcessRepository;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        SuitProcess suitProcess = (SuitProcess) delegateExecution.getVariable("processInstance");

        Suit suit = new Suit();
        suit.setSuitNumber(suitProcess.getSuit().getSuitNumber());

        Example<Suit> suitExample = Example.of(suit);

        List<Suit> existing = this.suitRepository.findAll(suitExample);

        if (existing.size() > 1) {
            long deletedId = suitProcess.getSuit().getId();
            suitRepository.deleteById(deletedId);
            Suit existingSuit = existing.stream().filter(s -> s.getId() != deletedId).findFirst().get();
            suitProcess.setSuit(existingSuit);
            suitProcess = suitProcessRepository.save(suitProcess);
        }

        delegateExecution.setVariable("suitAlreadyExistsInDatabase", existing.size() > 1);
    }
}
