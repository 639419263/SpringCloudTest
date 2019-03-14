package com.chenhao.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

    /**
     * 生成restful 风格的在线文档
     * @return
     */
    @Bean
    public Docket createRestfulApi(){
        return new Docket(DocumentationType.SWAGGER_2)
           .apiInfo(apiInfo())
                .enable(true)    //是否开启文档
              .select()
                .apis(RequestHandlerSelectors.basePackage("com.chenhao"))// 指定扫描包下面的注解
                  .paths(PathSelectors.any())
                     .build()
                       .pathMapping("/");
    }

    /**
     * 自定义API信息
     * @return
     */
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("集成Swagger2构建RESTful APIs")
                .description("集成Swagger2构建RESTful APIs")
                .termsOfServiceUrl("http://blog.csdn.net/java_yes")
                .contact(new Contact("chenhao","https://blog.lqdev.cn/","chenhao@hello.cn"))
                .version("1.0.0")
                .build();
    }
}
