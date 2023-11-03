package com.oracle.s202350104.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oracle.s202350104.model.Areas;
import com.oracle.s202350104.model.Board;
import com.oracle.s202350104.model.Spot;
import com.oracle.s202350104.model.SpotContent;
import com.oracle.s202350104.service.AreaService;
import com.oracle.s202350104.service.BoardService;
import com.oracle.s202350104.service.Paging;
import com.oracle.s202350104.service.SpotService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class SpotController {
	private final SpotService ss;
	private final AreaService as;
	private final BoardService boardService;

	@RequestMapping(value = "spot")
	public String spot(SpotContent spotContent,String currentPage, Model model) {
		UUID transactionId = UUID.randomUUID();
		try {
			log.info("[{}]{}:{}",transactionId, "spot", "start");
			int totalSpot = ss.totalSpot();
		
			Paging page = new Paging(totalSpot, currentPage);
			spotContent.setStart(page.getStart());
			spotContent.setEnd(page.getEnd());
		
			List<SpotContent> listSpot = ss.listSpot(spotContent);
			List<Areas> listAreas = as.listPoint();
		
			model.addAttribute("totalSpot",totalSpot);
			model.addAttribute("listSpot", listSpot);
			model.addAttribute("listAreas",listAreas);
			model.addAttribute("page",page);
		} catch (Exception e) {
			log.error("[{}]{}:{}",transactionId,  "spot", e.getMessage());
		}finally {
			log.info("[{}]{}:{}",transactionId, "spot", "end");
		}
		return "spot/spotList";
	}
	
	@RequestMapping(value = "spot/detail")
	public String spotDetail(int contentId, String currentPage, Board board, Model model) {
		UUID transactionId = UUID.randomUUID();
		try {
			log.info("[{}]{}:{}",transactionId, "spot/detail", "start");
			SpotContent spot = ss.detailSpot(contentId);
			
			model.addAttribute("contentId",contentId);
			model.addAttribute("spot",spot);
		} catch (Exception e) {
			log.error("[{}]{}:{}",transactionId,  "spot/detail", e.getMessage());
		} finally {
			log.info("[{}]{}:{}",transactionId, "spot/detail", "end");
		}
		
		/*
		 * review Logic 구간 
		 * by 엄민용
		 * */
		log.info("SpotController review Start!!");
		int bigCode = 2;
		// 분류 code 강제 지정
		int smallCode = 6;
		int userId = 1;
		int countBoard = 0;
		
		try {
			// smallCode를 이용해 countBoard를 설정
			countBoard = boardService.boardCount(smallCode);
			
			// Paging 작업
			// Parameter board page 추가
			Paging page = new Paging(countBoard, currentPage);
			board.setStart(page.getStart());
			board.setEnd(page.getEnd());
			board.setContent_id(contentId);
			log.info("SpotController reviewBoardList before board.getStart : {} ", board.getStart());
			log.info("SpotController reviewBoardList before board.getEnd : {} ", board.getEnd());
			log.info("SpotController reviewBoardList before board.getEnd : {} ", board.getContent_id());
			
			List<Board> revicewAllList = boardService.getReviewAllList(board); 
			log.info("SpotController revicewAllList size : {}", revicewAllList.size());

			log.info("SpotController reviewBoardList after board.getStart : {} ", board.getStart());
			log.info("SpotController reviewBoardList after board.getEnd : {} ", board.getEnd());

			bigCode = revicewAllList.get(0).getBig_code();

			log.info("SpotController reviewBoardList totalBoard : {} ", countBoard);
			log.info("SpotController reviewBoardList smallCode : {} ", smallCode);
			log.info("SpotController reviewBoardList page : {} ", page);

			model.addAttribute("reviewBoard", revicewAllList);
			model.addAttribute("page", page);
			model.addAttribute("bigCode", bigCode);
			model.addAttribute("smallCode", smallCode);
			model.addAttribute("userId", userId);
		} catch (Exception e) {
			log.error("SpotController review error : {}", e.getMessage());
		} finally {
			log.info("SpotController review end..");
		}
		
		return "spot/spotDetail";
	}
}
