package br.com.site.pessoa;

        import org.springframework.boot.SpringApplication;
        import org.springframework.boot.autoconfigure.SpringBootApplication;
        import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ReactPessoaApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReactPessoaApiApplication.class, args);
    }

}
