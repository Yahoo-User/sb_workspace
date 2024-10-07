package org.zerock.myapp.seq_5.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = "user-defined")								// OK with Java Record
public record UserDefinedProperties2 (
		// Note1: A record component name cannot have modifiers
		String name, Integer age,
		String prop1, String prop2, String prop3, String prop4,
		String myProperty		// user-defined.my_property
) {} // end class

