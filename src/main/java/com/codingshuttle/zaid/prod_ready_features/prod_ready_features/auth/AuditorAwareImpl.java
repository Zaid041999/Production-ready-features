package com.codingshuttle.zaid.prod_ready_features.prod_ready_features.auth;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
       //get the security context
        //get authentication
        //get the principle
        //get the username
        return Optional.of("Zaid Irfan Khan");
    }
}
