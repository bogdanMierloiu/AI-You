package com.bogdanmierloiu.springai.config;

import com.bogdanmierloiu.springai.entity.User;
import com.bogdanmierloiu.springai.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Keys;
import org.web3j.crypto.Sign;
import org.web3j.utils.Numeric;

import java.math.BigInteger;
import java.security.SignatureException;
import java.util.Arrays;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceConfig implements UserDetailsService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepo.findByEmail(email)
                .orElseThrow(() -> new BadCredentialsException("Invalid username"));
    }

    public Authentication authenticate(String username, String password) {
        UserDetails userDetails = loadUserByUsername(username);
        validatePassword(password, userDetails.getPassword());
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

    public Authentication authenticateMetamask(String address, String signature) {
        User user = userRepo.findByAddress(address).orElseThrow(
                () -> new BadCredentialsException("Invalid address")
        );
        isSignatureValid(signature, address, user.getNonce());
        return new UsernamePasswordAuthenticationToken(user.getAddress(), null, user.getAuthorities());
    }

    public void isSignatureValid(String signature, String address, Integer nonce) {
        // Compose the message with nonce
        String message = "Signing a message to login: %s".formatted(nonce);

        // Extract the ‘r’, ‘s’ and ‘v’ components
        byte[] signatureBytes = Numeric.hexStringToByteArray(signature);
        byte v = signatureBytes[64];
        if (v < 27) {
            v += 27;
        }
        byte[] r = Arrays.copyOfRange(signatureBytes, 0, 32);
        byte[] s = Arrays.copyOfRange(signatureBytes, 32, 64);
        Sign.SignatureData data = new Sign.SignatureData(v, r, s);

        // Retrieve public key
        BigInteger publicKey;
        try {
            publicKey = Sign.signedPrefixedMessageToKey(message.getBytes(), data);
        } catch (SignatureException e) {
            throw new BadCredentialsException("Invalid signature");
        }

        // Get recovered address and compare with the initial address
        String recoveredAddress = "0x" + Keys.getAddress(publicKey);
        if (!address.equalsIgnoreCase(recoveredAddress)) {
            throw new BadCredentialsException("Invalid signature");
        }
    }


    private void validatePassword(String inputPassword, String userPassword) {
        if (!passwordEncoder.matches(inputPassword, userPassword)) {
            throw new BadCredentialsException("Invalid password");
        }
    }

    @Bean
    public Random random() {
        return new Random();
    }
}
