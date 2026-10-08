package web.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@PropertySource("classpath:db.properties")
@ComponentScan(
        basePackages = "web",
        excludeFilters = {
                @ComponentScan.Filter(
                        type = FilterType.ANNOTATION,
                        classes = Controller.class
                )
        }
)
@EnableTransactionManagement
public class AppConfig {

    private final Environment env;

    @Autowired
    public AppConfig(Environment env) {
        this.env = env;
    }

    @Primary
    @Bean(name = "mysqlDataSource")
    public DataSource mysqlDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(env.getProperty("db.driver"));
        dataSource.setUrl(env.getProperty("db.url"));
        dataSource.setUsername(env.getProperty("db.username"));
        dataSource.setPassword(env.getProperty("db.password"));
        dataSource.setInitialSize(env.getProperty("db.initialSize", Integer.class, 5));
        dataSource.setMaxTotal(env.getProperty("db.maxTotal", Integer.class, 10));
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(mysqlDataSource());
        emf.setPackagesToScan(env.getProperty("hibernate.packagesToScan", "web.model"));

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setShowSql(env.getProperty("hibernate.show_sql", Boolean.class, true));
        vendorAdapter.setGenerateDdl(true);
        vendorAdapter.setDatabasePlatform(env.getProperty("hibernate.dialect"));
        emf.setJpaVendorAdapter(vendorAdapter);

        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto", "update"));
        properties.setProperty("hibernate.format_sql", env.getProperty("hibernate.format_sql", "true"));
        properties.setProperty("hibernate.use_sql_comments", env.getProperty("hibernate.use_sql_comments", "true"));
        emf.setJpaProperties(properties);

        return emf;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }
}