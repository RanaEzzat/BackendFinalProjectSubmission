package io.javabrains.springbootquickstart.courseapidata;


import io.javabrains.springbootquickstart.courseapidata.models.AuthenticationRequest;
import io.javabrains.springbootquickstart.courseapidata.models.AuthenticationResponse;
import io.javabrains.springbootquickstart.courseapidata.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
public class HomeController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private MyUserDetailsService userDetailsService;
    @Autowired
    private JwtUtil jwtTokenUtil;
    @RequestMapping("/")
    public String home()
    {
        return("<h1>Welcome</h1>");
    }

    @RequestMapping("/user")
    public String user()
    {
        return("<h1>Welcome to the user</h1>");
    }

    @GetMapping("/admin")
    public String admin()
    {
        return("<h1>Welcome to the admin</h1>");
    }

    @RequestMapping(value="/authenticate",method= RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception
    {
        try{
      authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),authenticationRequest.getPassword())
      );}
        catch(BadCredentialsException e)
        {
            throw new Exception("Incorrect username or password");
        }

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}
