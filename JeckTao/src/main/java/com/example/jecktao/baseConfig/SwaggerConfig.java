package com.example.jecktao.baseConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
@ComponentScan("com.example.jecktao.controller")
public class SwaggerConfig {
    /**
     * 一个api分组的配置，如果有多个分组，则添加多个这样的方法
     * @return
     */
    @Bean
    public Docket docker(){
        // 构造函数传入初始化规范，这是swagger2规范
        return new Docket(DocumentationType.SWAGGER_2)
                //apiInfo： 添加api详情信息，参数为ApiInfo类型的参数，这个参数包含了第二部分的所有信息比如标题、描述、版本之类的，开发中一般都会自定义这些信息
                .apiInfo(apiInfo())
                .groupName("base")
                //配置是否启用Swagger，如果是false，在浏览器将无法访问，默认是true
                .enable(true)
                .select()
                //apis： 添加过滤条件,
                .apis(RequestHandlerSelectors.basePackage("com.example.jecktao.controller"))
                //paths： 这里是控制哪些路径的api会被显示出来，比如下方的参数就是除了/user以外的其它路径都会生成api文档
                .paths((String a) ->
                            !a.equals("/user"))
                .build();
    }

    private ApiInfo apiInfo(){
        Contact contact = new Contact("名字：雷涛", "个人链接：", "邮箱：1657172677.com");
        return new ApiInfo(
                "涛帅的秘密基地API", // 标题
                "后台接口api", // 描述
                "版本内容：v1.0", // 版本
                "链接： ", // 组织链接
                contact, // 联系人信息
                "许可：Apach 2.0 ", // 许可
                "许可链接：", // 许可连接
                new ArrayList<>()// 扩展
        );
    }
}
