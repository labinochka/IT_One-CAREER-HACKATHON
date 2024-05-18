package ru.itis.financeimpl.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.financeapi.dto.request.AuthRequest;
import ru.itis.financeapi.dto.response.AuthResponse;
import ru.itis.financeimpl.security.service.AuthenticationService;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/sign-in")
    public AuthResponse signIn(@RequestBody AuthRequest authenticationRequest){
        return authenticationService.authenticate(authenticationRequest);
    }
    // TODO: implement refresh token
//    @PostMapping("/refresh-tokens")
//    private TokenCoupleResponse refreshToken(@RequestBody TokenCoupleRequest tokenCoupleRequest) {
//        return authenticationService.refreshToken(tokenCoupleRequest);
//    }

//  TODO: implement logout

//    @PostMapping("/logout")
//    private void logout(@RequestBody TokenCoupleRequest tokenCoupleRequest) {
//        authenticationService.logout(tokenCoupleRequest);
//    }


}
