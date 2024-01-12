package com.danilo.elasticsearch.configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Swagger {

    @Bean
    public OpenAPI bankApi() {
        OpenAPI api = new OpenAPI();
        api.setInfo(this.getInfo());
        api.setExternalDocs(this.getExternalDocumentation());
        return api;
    }

    private Info getInfo() {
        Info information = new Info();
        information.setTitle("Rest API");
        information.setDescription("Sysbank");
        information.setVersion("1.0");
        information.setTermsOfService("Terms of Service");
        information.setLicense(this.getLicense());
        return information;
    }

    private License getLicense() {
        License license = new License();
        license.setName("Apache 2.0");
        license.setUrl("https://www.apache.org/licenses/LICENSE-2.0");
        return license;
    }

    private ExternalDocumentation getExternalDocumentation() {
        ExternalDocumentation documentation = new ExternalDocumentation();
        documentation.setDescription("Danilo Gonçalves Vicente");
        documentation.setUrl("https://www.linkedin.com/in/danilo-gonçalves-vicente-a42a09a4");
        return documentation;
    }

}
