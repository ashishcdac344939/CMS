package in.fridr.security;

import javax.sql.DataSource;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import in.fridr.service.MyUserDetailsService;
import in.fridr.service.UserServices;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter  {
	@Autowired
	private MyUserDetailsService myUserDetailsService;
	@Autowired
	private Environment env;
	@Autowired
	UserServices userService;
	@Autowired
	DataSource dataSource;
	@Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;
	@Autowired
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

	
	public SecurityConfig(MyUserDetailsService userDetailsServiceImpl) {
        this.myUserDetailsService = userDetailsServiceImpl;
    }
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// Enable jdbc authentication
	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(passwordEncoder());
	}

	
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	    //Beans
	    @Bean
	    public BCryptPasswordEncoder bCryptPasswordEncoder() {
	        return new BCryptPasswordEncoder();
	    }

	    @Bean
	    public ModelMapper modelMapper() {
	        return new ModelMapper();
	    }

	    @Bean
	    public DaoAuthenticationProvider authProvider() {
	        final DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	        authProvider.setUserDetailsService(myUserDetailsService);
	        authProvider.setPasswordEncoder(bCryptPasswordEncoder());
	        return authProvider;
	    }

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable();
		http 	.authorizeRequests().antMatchers("/user/register").permitAll()
				.antMatchers("/doctorForRest/register").permitAll()
				.antMatchers("/password/forgot_password/**","/password/reset_password/**").permitAll()
				.antMatchers("/js/**").permitAll()
				.antMatchers("/dist/**").permitAll().antMatchers("/assets/**").permitAll().antMatchers("/login/authenticate").permitAll()
				.antMatchers("/login/getOtp").permitAll().antMatchers("/login/authenticateByWeb").permitAll().antMatchers("/doctor/doctorDashboard").permitAll() 
				.antMatchers("/doctor/**","/user/**","/medicine/**").hasAnyRole("DOCTOR","PHARMACIST","PATIENT").anyRequest().authenticated()
				.and()
				.formLogin()
                .loginPage("/login/loginForm")  
                .permitAll()
                .loginProcessingUrl("/submitLoginForm")
                .usernameParameter("username")
                .passwordParameter("password")
                .successHandler(customAuthenticationSuccessHandler)
                .defaultSuccessUrl("/user/home") // Specify the default success URL
                .failureUrl("/login/loginError")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login/loginForm")
                .permitAll()
             
                    .and()
                .headers()
                    .cacheControl() // Disable caching
                    .disable();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		 auth.authenticationProvider(customAuthenticationProvider);
		//auth.userDetailsService(myUserDetailsService);
	}
//	    @Bean
//	    public RememberMeServices rememberMeServices() {
//	        return new CustomRememberMeServices("theKey",
//	                userDetailsServiceImpl, new InMemoryTokenRepositoryImpl());
//	    }
}
