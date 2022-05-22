import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@MapperScan("com.mytest.dao.mapper")
@SpringBootApplication(scanBasePackages={"com.mytest.*"}
//    , exclude = {DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class, HibernateJpaAutoConfiguration.class}
    )
public class MyTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyTestApplication.class);
    }

}
