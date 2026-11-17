package com.uma.supplychain.controller;
import com.uma.supplychain.config.*;
import com.uma.supplychain.dto.ApiDtos.*;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
@RestController @RequestMapping("/api/auth")
public class AuthController {
 private final JwtService jwt; private final PasswordEncoder encoder;
 public AuthController(JwtService jwt,PasswordEncoder encoder){this.jwt=jwt;this.encoder=encoder;}
 @PostMapping("/login") public ResponseEntity<?> login(@Valid @RequestBody LoginRequest r){
  var user=DataSeeder.USERS.get(r.username());
  if(user==null||!encoder.matches(r.password(),user.passwordHash()))return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message","Invalid username or password"));
  return ResponseEntity.ok(new LoginResponse(jwt.generate(r.username(),user.role()),r.username(),user.role()));
 }
}
