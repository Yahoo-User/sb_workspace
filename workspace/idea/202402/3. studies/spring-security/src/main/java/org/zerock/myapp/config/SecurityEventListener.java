package org.zerock.myapp.config;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.*;
import org.springframework.security.web.authentication.session.SessionFixationProtectionEvent;
import org.springframework.stereotype.Component;


@Log4j2
@NoArgsConstructor

@Component
public class SecurityEventListener {


    // ---------------------------------------------------- //
    // 1. To handle authentication success events.
    // ---------------------------------------------------- //

    @EventListener
    public void handleAuthenticationSuccess(AuthenticationSuccessEvent event) {
        log.trace("1. handleAuthenticationSuccess({}) invoked.", event);

    } // handleAuthenticationSuccess

    @EventListener
    public void handleSessionFixationProtection(SessionFixationProtectionEvent event) {
        log.trace("2. handleSessionFixationProtection({}) invoked.", event);

    } // handleSessionFixationProtection

    @EventListener
    public void handleInteractiveAuthenticationSuccess(InteractiveAuthenticationSuccessEvent event) {
        log.trace("3. handleInteractiveAuthenticationSuccess({}) invoked.", event);

    } // handleInteractiveAuthenticationSuccess


    // ---------------------------------------------------- //
    // 2. To handle authentication failure events.
    // ---------------------------------------------------- //

    @EventListener
    public void handleBadCredential(AuthenticationFailureBadCredentialsEvent event) {
        log.trace("1. handleBadCredential({}) invoked.", event);

    } // handleBadCredential

    @EventListener
    public void handleDisabled(AuthenticationFailureDisabledEvent event) {
        log.trace("2. handleDisabled({}) invoked.", event);

    } // handleDisabled

    @EventListener
    public void handleExpired(AuthenticationFailureExpiredEvent event) {
        log.trace("3. handleExpired({}) invoked.", event);

    } // handleExpired

    @EventListener
    public void handleLocked(AuthenticationFailureLockedEvent event) {
        log.trace("4. handleLocked({}) invoked.", event);

    } // handleLocked

    @EventListener
    public void handleCredentialsExpired(AuthenticationFailureCredentialsExpiredEvent event) {
        log.trace("5. handleCredentialsExpired({}) invoked.", event);

    } // handleCredentialsExpired

    @EventListener
    public void handleProviderNotFound(AuthenticationFailureProviderNotFoundEvent event) {
        log.trace("6. handleProviderNotFound({}) invoked.", event);

    } // handleProviderNotFound

    @EventListener
    public void handleServiceException(AuthenticationFailureServiceExceptionEvent event) {
        log.trace("7. handleServiceException({}) invoked.", event);

    } // handleServiceException

} // end class
