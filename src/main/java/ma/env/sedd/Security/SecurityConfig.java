package ma.env.sedd.Security;
// Regles de securite

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.Md4PasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired // datasource specifiee dans le fichier des proprietes
    private DataSource dataSource;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /*
        auth.inMemoryAuthentication().withUser("admin").password("{noop}1234").roles("USER", "ADMIN");
        auth.inMemoryAuthentication().withUser("user").password("{noop}0000").roles("USER");*/

        auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery("select nom as principal, CONCAT('{noop}',password) as credentials, 'true' as enabled from user where nom=? ").
                authoritiesByUsernameQuery("select nom as principal, role as role from user where nom=?").rolePrefix("ROLE_");
        auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery("select nom as principal, CONCAT('{noop}',password) as credentials,'true' as enabled from intervenant where nom=? ").
                authoritiesByUsernameQuery("select nom as principal, role as role from intervenant where nom=?").rolePrefix("ROLE_");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin();
        http.authorizeRequests().antMatchers("/*").hasRole("Operator");
        http.authorizeRequests().antMatchers("/*").hasRole("Admin");
        http.authorizeRequests().antMatchers("intervention","mylogs").hasRole("Intervenant");
        http.exceptionHandling().accessDeniedPage("/403");
    }
}
