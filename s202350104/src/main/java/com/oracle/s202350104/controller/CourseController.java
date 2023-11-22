package com.oracle.s202350104.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oracle.s202350104.model.Banner;
import com.oracle.s202350104.model.Board;
import com.oracle.s202350104.model.Course;
import com.oracle.s202350104.model.CourseContent;
import com.oracle.s202350104.service.BannerService;
import com.oracle.s202350104.service.CourseService;
import com.oracle.s202350104.service.Paging;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class CourseController {
	
	private final CourseService cs;
	private final BannerService bannerService;
	
	/* 전체적으로 각 Method들이 무슨 기능을 하고 있는지 간략하게 주석을 남겨주시면 다른 분들도 이해하기 좋을 것  같아요.
	 * by.엄민용
	 */ 
	
	// Course 유저 리스트 페이지로 넘어가는 logic
	@RequestMapping(value = "course")
	public String courses(Course course, String currentPage, Model model) {
		UUID transactionId = UUID.randomUUID();
		
		try {
			log.info("[{}]{}:{}",transactionId, "Course", "start");
			
			// 코스의 전체 list의 수를 나타냄.
			int courseCount = cs.courseCount(course);
			
			// 페이징 처리
			Paging page = new Paging(courseCount, currentPage);
			course.setStart(page.getStart());
			course.setEnd(page.getEnd());
			
			// Course의 리스트를 출력
			log.info("CourseController courseList start...");
			List<Course> courseList = cs.courseList(course);
			log.info("courseList : " + courseList);
			
			model.addAttribute("courseCount", courseCount);
			model.addAttribute("courseList", courseList);
			model.addAttribute("page", page);
			
			/*
			 * Banner Logic 구간 
			 * by 엄민용
			 * */
			List<Banner> bannerHeader = bannerService.getHeaderBanner();
			List<Banner> bannerFooter = bannerService.getFooterBanner();
			
			model.addAttribute("bannerHeader", bannerHeader);
			model.addAttribute("bannerFooter", bannerFooter);
			
		} catch (Exception e) {
			log.error("CourseController courses e.getMessage() ->" + e.getMessage());
		} finally {
			log.info("CourseController courses end");
		}
		
		return "course/courseList";
	}
	
	
	@RequestMapping(value = "course/detail")
	public String courseDetail(Course course, Model model) {
		try {
			log.info("course" + course);
			log.info("CourseController courseDetail course.getCourse_id() ->" + course.getCourse_id());
			
			List<Course> courseDetailList = cs.courseDetail(course.getCourse_id());
			log.info("CourseController courseDetail courseDetail ->" + courseDetailList.size());
			log.info("courseDetailList : " + courseDetailList);
			
			model.addAttribute("courseDetail", courseDetailList);
		} catch (Exception e) {
			log.error("CourseController courseDetail e.getMessage() ->" + e.getMessage());
		} finally {
			log.info("CourseController courseDetai end");
		}
		
		return "course/courseDetail";
	}
}
