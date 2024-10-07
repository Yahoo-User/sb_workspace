package org.zerock.myapp.seq_1.domain;

import java.util.Date;


public record BoardVO(
	// Important:	A record component seq cannot have modifiers
	Long seq, String title, String writer, String content, Integer cnt, Date createDate, Date updateDate
) {} // end record


