package controller;


import domain.dto.ReviewResponse;
import domain.dto.HospitalResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.ReviewService;

import java.util.List;

@RestController
@RequestMapping("api/v1/reviews")
@RequiredArgsConstructor //필요한 생성자를 자동 생성해주는 어노테이션
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping("") // 리뷰 목록
    public ResponseEntity<List<ReviewResponse>> reviewList(Pageable pageable){
        return ResponseEntity.ok().body(reviewService.getReviews(pageable));
    }

    @GetMapping("/{id}") //리뷰 한개
    public ResponseEntity<ReviewResponse> getReview(@PathVariable Long id) { // ResponseEntity도 DTO타입
        ReviewResponse reviewResponse = reviewService.getReview(id); // DTO
        return ResponseEntity.ok().body(reviewResponse); // Return은 DTO로
    }
}