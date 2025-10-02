package com.scm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.scm.services.Imp.SecurityCustomUserDeatilService;

@Configuration
public class SecurityConfig {

    @Autowired
    private AuthenticationSucessHandler handler;

    @Autowired
    private SecurityCustomUserDeatilService userDeatilService;

    //Configuration of authetication provider
    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();

        //user detail service object
        daoAuthenticationProvider.setUserDetailsService(userDeatilService);

        //password Encoder object
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());

        return daoAuthenticationProvider;


    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        

        //configuration 

        //urls confuration
        httpSecurity.authorizeHttpRequests(authorize->{
            // authorize.requestMatchers("/home","/register","/services").permitAll();
            authorize.requestMatchers("/user/**").authenticated();
            authorize.anyRequest().permitAll();
        });

        //from default login
        httpSecurity.formLogin(formLogin->{

            formLogin.loginPage("/login");
            formLogin.loginProcessingUrl("/authenticate");
            // formLogin.successForwardUrl("/user/dashboard");
            // formLogin.failureForwardUrl("/login?error=true");
             formLogin.defaultSuccessUrl("/user/dashboard", true); // ✅ redirect GET
            formLogin.failureUrl("/login?error=true");   

            formLogin.usernameParameter("email");
            formLogin.passwordParameter("password");
           

            // formLogin.failureHandler(new AuthenticationFailureHandler() {
            //     @Override
            //     public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
            //         throw new UnsupportedOperationException("Not supported yet.");
            //     }
                
            // });
            

        });

        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        // before gpt
        httpSecurity.logout(logoutForm->{
            logoutForm.logoutUrl("/do-logout");
            logoutForm.logoutSuccessUrl("/login?logout=true");


        });
//          httpSecurity.logout(logoutForm -> {
//     logoutForm.logoutUrl("/do-logout");
//     logoutForm.logoutSuccessHandler((request, response, authentication) -> {
//         request.getSession().invalidate();
//         response.sendRedirect("https://accounts.google.com/Logout");
//     });
// });


       
         httpSecurity.oauth2Login(oauth->{
            oauth.loginPage("/login");
            oauth.successHandler(handler);
         });



      




        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
  