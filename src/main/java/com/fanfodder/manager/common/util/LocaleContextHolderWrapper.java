package com.fanfodder.manager.common.util;

import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class LocaleContextHolderWrapper {

	private static final Logger LOGGER = LogManager.getLogger(LocaleContextHolderWrapper.class);

    public Locale getCurrentLocale() {
        LOGGER.debug("Getting current locale");
        return LocaleContextHolder.getLocale();
    }
}
