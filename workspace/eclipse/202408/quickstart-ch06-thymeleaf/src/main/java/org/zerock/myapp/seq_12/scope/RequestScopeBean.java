package org.zerock.myapp.seq_12.scope;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import org.zerock.myapp.common.CommonBeanCallbacks;
import org.zerock.myapp.util.RandomNumberGenerator;

import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = false)
@Data

/**
 * Important:
 * 		(1) All Scoped Beans Should Be Injected To The Declared Field.
 * 		(2) All Scoped Beans Should *Not Be Injected To The *Method *Parameters.		(***)
 */

//@Scope("request")
@RequestScope

@Component("requestScopeBean")
public class RequestScopeBean extends CommonBeanCallbacks {
	private Integer data = RandomNumberGenerator.generateInt(1, 45);
	private String ref = Integer.toHexString(super.hashCode());
	
	
	
} // end class

