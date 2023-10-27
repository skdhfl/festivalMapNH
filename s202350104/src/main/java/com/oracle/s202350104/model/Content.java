package com.oracle.s202350104.model;

import java.util.Date;

import lombok.Data;

@Data
public class Content {
	private int id;
	private int area;
	private int sigungu;
	private int big_code;
	private int small_code;
	private String title;
	private String content;
	private String status;
	private String img1;
	private String img2;
	private String img3;
	private String address;
	private int postcode;
	private String mapx;
	private String mapy;
	private String email;
	private String phone;
	private String homepage;
	private int readcount;
	private String user_id;
	private Date created_at;
	private Date deleted_at;
	private String is_deleted;
}
