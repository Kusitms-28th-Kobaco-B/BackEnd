package kobako.backend.global.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
    info = @Info(
        title = "Kobako 기업 프로젝트",
        description = "Kobako Server API 명세서",
        version = "v1"
    )
)
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().servers(List.of(
//            new Server().url("http://3.35.60.56:8080/").description("Kobako API Server")));
            new Server().url("http://localhost:8080/").description("Kobako API Server")));

    }
}