package vn.iotstar.config;

import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Dang ky Thymeleaf Layout Dialect de dung layout:decorate / layout:fragment
 * trong cac file HTML (layout-admin.html, list.html, addOrEdit.html...).
 */
@Configuration
public class ThymeleafConfig {

    @Bean
    public LayoutDialect layoutDialect() {
        return new LayoutDialect();
    }
}
