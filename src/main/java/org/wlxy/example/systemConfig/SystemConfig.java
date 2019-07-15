package org.wlxy.example.systemConfig;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import springfox.documentation.service.Contact;

@Component
@ConfigurationProperties(prefix = "app")
@Data
public class SystemConfig {
    private  String info;
    private  String auther;
    private  String emal;
    private  String swaggerTitle;
    private  String swaggerContactName;
    private  String swaggerContactWebUrl;
    private  String swaggerContactEmail;
    private  String swaggerVersion;
    private  String swaggerDescription;
    private  String swaggerTermsOfServiceUrl;

}
