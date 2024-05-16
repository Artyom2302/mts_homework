package ru.mtsbank.bpp;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Configuration;
import ru.mtsbank.service.CreateAnimalServiceImpl;

@Configuration
public class CreateServiceBeanPostProcessor implements BeanPostProcessor{
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof CreateAnimalServiceImpl){
            CreateAnimalServiceImpl createAnimalServiceImpl = (CreateAnimalServiceImpl) bean;
            createAnimalServiceImpl.setType(createAnimalServiceImpl.getRandomAnimalType());
            return createAnimalServiceImpl;
        }
        return bean;
    }
}
