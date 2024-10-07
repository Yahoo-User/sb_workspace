package org.zerock.myapp.seq_13.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.myapp.common.CommonBeanCallbacks;
import org.zerock.myapp.seq_12.scope.SessionScopeBean;

import lombok.extern.log4j.Log4j2;


@Log4j2

@Service("scopeService")
public class ScopeService extends CommonBeanCallbacks {
	/**
	 * Important:
	 * 		(1) All Scoped Beans Should Be Injected To The Declared Field.
	 * 		(2) All Scoped Beans Should *Not Be Injected To The *Method *Parameters.		(***)
	 */
	@Autowired private SessionScopeBean sessionScopeBean;
	
	
	@Autowired
	SessionScopeBean doit() {
		log.trace("doit({}) invoked.");
		log.info("  + this.sessionScopeBean: {}", this.sessionScopeBean);
		
		return this.sessionScopeBean;
	} // doit

} // end class

