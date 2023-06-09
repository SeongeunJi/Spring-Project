package jpabook.jpashop;

import jpabook.jpashop.domain.consts.Country;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {
    private final HandlerInterceptor loginInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/css/**","/*.ico","/login","/members/new","/","/info","/error");
    }

    @Bean
    public Map<String, Country> countryMap() {
        Map<String, Country> countryMap = new LinkedHashMap<>();
        countryMap.put(Country.AUSTRALIA.name(), Country.AUSTRALIA);
        countryMap.put(Country.CANADA.name(), Country.CANADA);
        countryMap.put(Country.CHINA.name(), Country.CHINA);
        countryMap.put(Country.FRANCE.name(), Country.FRANCE);
        countryMap.put(Country.GERMANY.name(), Country.GERMANY);
        countryMap.put(Country.JAPAN.name(), Country.JAPAN);
        countryMap.put(Country.KOREA.name(), Country.KOREA);
        countryMap.put(Country.USA.name(), Country.USA);
        return countryMap;
    }
}
