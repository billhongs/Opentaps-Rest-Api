package org.opentaps.module.ws.rest.security;

import org.ofbiz.base.crypto.HashCrypt;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.encoding.PasswordEncoder;

/**
 * Created by IntelliJ IDEA.
 * User: alobrano
 * Date: 9/30/11
 * Time: 5:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class OpentapsPasswordEncoder implements PasswordEncoder{

    public OpentapsPasswordEncoder() {
    }

    public String encodePassword(String rawPass, Object salt) throws DataAccessException {
        return HashCrypt.getDigestHash(rawPass);
    }

    public boolean isPasswordValid(String encPass, String rawPass, Object salt) throws DataAccessException {
        boolean result = false;
        String digestPassword = HashCrypt.getDigestHash(rawPass);
        if(encPass.equals(digestPassword)){
            result = true;
        }
        return result;
    }
}
