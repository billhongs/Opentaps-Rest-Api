package org.opentaps.module.ws.rest.security;

import org.ofbiz.base.util.Debug;
import org.ofbiz.entity.Delegator;
import org.ofbiz.entity.DelegatorFactory;
import org.ofbiz.service.GenericDispatcher;

import org.opentaps.base.entities.UserLogin;
import org.opentaps.base.entities.UserLoginSecurityGroup;
import org.opentaps.foundation.entity.EntityNotFoundException;
import org.opentaps.foundation.infrastructure.Infrastructure;
import org.opentaps.foundation.infrastructure.InfrastructureException;
import org.opentaps.foundation.infrastructure.User;
import org.opentaps.foundation.repository.RepositoryException;
import org.opentaps.foundation.repository.ofbiz.Repository;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: alobrano
 * Date: 9/29/11
 * Time: 4:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class OpentapsUserDetailService implements UserDetailsService {

    private static final String MODULE = OpentapsUserDetailService.class.getName() ;
    private final static String DELEGATOR_NAME = "default";
    private static String DISPATCHER_NAME = "default";



    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
        Repository repository = new Repository();
        Delegator delegator = DelegatorFactory.getDelegator(DELEGATOR_NAME);
        repository.setInfrastructure(new Infrastructure(GenericDispatcher.getLocalDispatcher(DISPATCHER_NAME, delegator)));
        UserLogin userLogin = null;
        try {
            userLogin = repository.findOneNotNull(UserLogin.class, repository.map(UserLogin.Fields.userLoginId, username));
        } catch (RepositoryException e) {
           //todo do something with this error.
            Debug.logError("Error communicating with the Repository",MODULE);
        } catch (EntityNotFoundException e) {
           throw new UsernameNotFoundException("User ["+username+"] not found",e);
        }


        OpentapsRestUserDetails opentapsRestUserDetails = new OpentapsRestUserDetails();
        opentapsRestUserDetails.setUsername(userLogin.getUserLoginId());
        opentapsRestUserDetails.setPassword(userLogin.getCurrentPassword());
        opentapsRestUserDetails.setAccountNonExpired(userLogin.getEnabled() == null ? true : userLogin.getEnabled().equals("Y"));
        opentapsRestUserDetails.setAccountNonLocked(userLogin.getEnabled()== null ? true : userLogin.getEnabled().equals("Y"));
        opentapsRestUserDetails.setCredentialsNonExpired(userLogin.getEnabled()== null ? true : userLogin.getEnabled().equals("Y"));
        opentapsRestUserDetails.setEnabled(userLogin.getEnabled()== null ? true : userLogin.getEnabled().equals("Y"));

        User user = null;
        try {
            user = new User(Repository.genericValueFromEntity(userLogin),delegator);
            opentapsRestUserDetails.setUser(user);
        } catch (RepositoryException e) {
           //todo do something with this error.
            Debug.logError("Error communicating with the Repository",MODULE);
        }

        try {
            Set<String> permissions = user.getPermissions();
            for (String permission : permissions) {
                opentapsRestUserDetails.addAuthority(permission);
            }

        } catch (InfrastructureException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        return opentapsRestUserDetails;
    }
}
