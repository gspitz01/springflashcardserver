package com.gregspitz.springflashcardserver.di;

import com.gregspitz.springflashcardserver.data.FlashcardRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public FlashcardRepository provideRepository() {
        return new FlashcardRepository();
    }
}
