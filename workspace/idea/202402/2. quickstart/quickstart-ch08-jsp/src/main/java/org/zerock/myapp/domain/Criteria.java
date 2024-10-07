package org.zerock.myapp.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.web.util.UriComponentsBuilder;


@Log4j2

@NoArgsConstructor
@Data
public class Criteria {
    // ========== 1. 기준정보 ========== //
    private Integer pageNumber = 1;     // 현재 표시할 페이지번호
    private Integer pageSize = 10;      // 한 페이지당 보여줄 레코드 건수
    private Integer pagesPerPage = 5;   // 한 페이지당 보여줄 페이지목록의 길이
    private Long    totalElements = 0L; // 현재 총 요소의 갯수
    private Integer totalPages = 0;     // 현재 총페이지의 갯수

    // ========== 2. 검색조건 ========== //
    private String  searchType;		    // 검색유형
    private String  searchKeyword;		// 검색어

    // ========== 3. Pagination ========== //
    private boolean prev;           // 이전 페이지의 존재여부
    private boolean next;           // 다음 페이지의 존재여부

    private int startPageNumber;    // 한 페이지당 페이지목록의 시작번호
    private int endPageNumber;		// 한 패이지당 페이지목록의 끝번호

    private boolean prevList;       // 다음 페이지목록의 존재여부
    private boolean nextList;	    // 이전 페이지목록의 존재여부


    public void initialize(Page<?> currPage) {
        log.debug("initialize({}) invoked.", currPage);

        //----------------------------------------------------------//
        //  Step.0 : 페이징 처리를 위한 공통변수 생성하기
        //----------------------------------------------------------//
        this.pageNumber     = currPage.getNumber() + 1;     // Page number starts with zero(0) (***)
        this.pageSize       = currPage.getSize();
        this.totalElements  = currPage.getTotalElements();
        this.totalPages     = currPage.getTotalPages();
        this.prev           = currPage.hasPrevious();
        this.next           = currPage.hasNext();

        //----------------------------------------------------------//
        // Step.1 : 현재 페이지에서 보여줄 페이지목록의 끝페이지번호 구하기
        //----------------------------------------------------------//
        // (공식) 끝페이지번호 = (int) Math.ceil( (double) 현재페이지번호 / 페이지목록길이 ) x 페이지목록길이
        //----------------------------------------------------------//
        this.endPageNumber = (int) Math.ceil( (this.pageNumber * 1.0) / this.pagesPerPage ) * this.pagesPerPage;

        //----------------------------------------------------------//
        // Step.2 : 현재 페이지에서 보여줄 페이지목록의 시작번호 구하기
        //----------------------------------------------------------//
        // (공식) 시작페이지번호 = 끝페이지번호 - ( 페이지목록길이 - 1 )
        //----------------------------------------------------------//
        this.startPageNumber = this.endPageNumber - ( this.pagesPerPage - 1 );

        //----------------------------------------------------------//
        // Step.3 : 실제 페이지목록의 끝페이지번호 구하기
        //----------------------------------------------------------//
        // (공식) 실제페이지목록끝번호 = (끝페이지번호 < 총페이지수) ? 끝페이지번호 : 총페이지수
        //----------------------------------------------------------//
        this.endPageNumber = (this.endPageNumber < this.totalPages)? this.endPageNumber : this.totalPages;

        //----------------------------------------------------------//
        // Step.4 : 이전페이지목록으로 이동가능여부(prev) 구하기
        //----------------------------------------------------------//
        // (공식) 이전페이지목록이동가능여부 = 시작페이지번호 > 1
        //----------------------------------------------------------//
        this.prevList = this.startPageNumber > 1;

        //----------------------------------------------------------//
        // Step.5 : 다음 페이지번호목록으로 이동가능여부(next) 구하기
        //----------------------------------------------------------//
        // (공식) 다음페이지목록이동가능여부 = 끝페이지번호 < 총페이지수
        //----------------------------------------------------------//
        this.nextList = this.endPageNumber < totalPages;
    } // initialize


    public String getPagingUri() {
        log.debug("getPagingUri() invoked.");

        UriComponentsBuilder builder = UriComponentsBuilder.fromPath("");
        builder.queryParam("pageNumber", this.pageNumber);
        builder.queryParam("pageSize", this.pageSize);
        builder.queryParam("pagesPerPage", this.pagesPerPage);

        if(this.searchType != null && !this.searchType.isEmpty())
            builder.queryParam("searchType", this.searchType);

        if(this.searchKeyword != null && !this.searchKeyword.isEmpty())
            builder.queryParam("searchKeyword", this.searchKeyword);

        return builder.toUriString();
    } // getPagingUri


} // end class
