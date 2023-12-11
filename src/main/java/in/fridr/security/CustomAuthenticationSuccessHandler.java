package in.fridr.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        // Set credentials in the Authentication object
    	Authentication userAuthentication = SecurityContextHolder.getContext().getAuthentication();
    	
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(authentication.getPrincipal(), authentication.getCredentials());
        auth.setDetails(authentication.getDetails());
        // Update the SecurityContextHolder with the modified Authentication object
        SecurityContextHolder.getContext().setAuthentication(auth);
        super.onAuthenticationSuccess(request, response, auth);
    }
}

