package com.awstraining.backend.business.notifyme;


import com.amazonaws.services.translate.AmazonTranslate;
import com.amazonaws.services.translate.model.TranslateTextRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TranslatorService implements Translator {

    private final AmazonTranslate amazonTranslate;

    @Autowired
    public TranslatorService(AmazonTranslate amazonTranslate) {

        this.amazonTranslate = amazonTranslate;
    }

    @Override
    public String translate(NotifyMeDO notifyMeDO) {
        TranslateTextRequest translateTextRequest = new TranslateTextRequest()
                .withText(notifyMeDO.text())
                .withSourceLanguageCode(notifyMeDO.sourceLc())
                .withTargetLanguageCode(notifyMeDO.targetLc());

        return amazonTranslate.translateText(translateTextRequest).getTranslatedText();
    }
}
