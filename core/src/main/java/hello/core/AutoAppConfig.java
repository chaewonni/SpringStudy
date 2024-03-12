package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes =
                Configuration.class))//스프링 빈을 쫙 긁어서 자동으로 스프링 빈으로 끌어 올려야 됨
public class AutoAppConfig {

}