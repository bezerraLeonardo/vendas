package bezerraLeonardo.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.Locale;

@Configuration
public class InternacionalizacaoConfig {

    @Bean
    public MessageSource messageSource(){

        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages"); //para referenciar o pacote de messages q foi criado no properties.
        messageSource.setDefaultEncoding("ISO-8859-1"); //para q a aplicação receba a codificação de caracteres no padrão ISO.
        messageSource.setDefaultLocale(Locale.getDefault()); //para q a aplicação receba o padrão de escrita local.
        return messageSource;

    }

    @Bean
    public LocalValidatorFactoryBean validatorFactoryBean(){
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        return bean;

//metodo para converter a chave message p as mensagens externaciolizadas
    }
}
