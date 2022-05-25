package com.openpayd.casestudy.dto;

import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageDto<T>  {

    private List<T> content;

    private long totalElements;

    private long totalPages;

}
