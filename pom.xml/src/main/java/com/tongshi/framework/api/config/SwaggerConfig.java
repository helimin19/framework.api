package com.tongshi.framework.api.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.tags.Tag;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.schema.ScalarType;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ParameterType;
import springfox.documentation.service.RequestParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
	
	@Bean("abc")
    public Docket createRestApi() {
        // 配置OAS 3.0协议
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .select()
                // 查找有@Tag注解的类，并生成一个对应的分组；类下面的所有http请求方法，都会生成对应的API接口
                // 通过这个配置，就可以将那些没有添加@Tag注解的控制器类排除掉
                .apis(RequestHandlerSelectors.withClassAnnotation(Tag.class))
                .paths(PathSelectors.any())
                .build()
                .globalRequestParameters(getGlobalRequestParameters());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("OAuth接口文档")
                .description("OAuth接口文档")
                .termsOfServiceUrl("http://localhost:8080/swagger-ui/index.html")
                .contact(new Contact("何利民", "http://www.tongshi.con", "46453868@qq.com"))
                .version("1.0.0")
                .build();
    }
    
	// 生成全局通用参数
	private List<RequestParameter> getGlobalRequestParameters() {
	    List<RequestParameter> parameters = new ArrayList<>();
	    parameters.add(new RequestParameterBuilder()
	            .name("Accept-Language")
	            .description("浏览器语言")
	            .required(false)
	            .in(ParameterType.HEADER)
	            .query(q -> q.model(m -> m.scalarModel(ScalarType.STRING)))
	            .required(false)
	            .build());
//	    parameters.add(new RequestParameterBuilder()
//	            .name("udid")
//	            .description("设备的唯一id")
//	            .required(true)
//	            .in(ParameterType.QUERY)
//	            .query(q -> q.model(m -> m.scalarModel(ScalarType.STRING)))
//	            .required(false)
//	            .build());
//	    parameters.add(new RequestParameterBuilder()
//	            .name("version")
//	            .description("客户端的版本号")
//	            .required(true)
//	            .in(ParameterType.QUERY)
//	            .query(q -> q.model(m -> m.scalarModel(ScalarType.STRING)))
//	            .required(false)
//	            .build());
	     return parameters;
	}

    
}
