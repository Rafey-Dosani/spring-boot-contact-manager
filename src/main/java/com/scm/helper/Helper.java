package com.scm.helper;


import org.springframework.security.core.Authentication;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class Helper {
    public static String getEmailOfLoggedInUser(Authentication authentication){

       

       


        //fetching the email id  
        


        if(authentication instanceof OAuth2AuthenticationToken){

            var aOAuth2AuthenticationToken=(OAuth2AuthenticationToken)authentication;
       var clientId=aOAuth2AuthenticationToken.getAuthorizedClientRegistrationId();
       var oauth2User=(OAuth2User)authentication.getPrincipal();
       String username="";
        
       
       

       if(clientId.equalsIgnoreCase("google"))
       {
        //sign in with google 
        System.out.println("Gettting eamil from google clint ");
        username = oauth2User.getAttribute("email").toString();
       }

       else if(clientId.equalsIgnoreCase("github"))
       {
          //sing in with github
        System.out.println("Getting email from github client");
        username=oauth2User.getAttribute("email") !=null ? oauth2User.getAttribute("email").toString() : oauth2User.getAttribute("login").toString()+"@gmail.com";

       }

       return username;

        //sign in with facebook 

        }
        else {
            //NOrmal email id , pass is used to register
            System.out.println("Getting data from local database ");
            return authentication.getName();
        }

        

    }
}
