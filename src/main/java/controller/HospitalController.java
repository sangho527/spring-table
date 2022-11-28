package controller;

import domain.dto.HospitalResponse;
import domain.dto.ReviewRequest;
import domain.dto.ReviewResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.HospitalService;
import service.ReviewService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/hospitals")
public class HospitalController {
    private final HospitalService hospitalService;
    private final ReviewService reviewService;

    public HospitalController(HospitalService hospitalService, ReviewService reviewService) {
        this.hospitalService = hospitalService;
        this.reviewService = reviewService;
    }

    @GetMapping("") // 병원 목록
    public ResponseEntity<List<HospitalResponse>> hospitalList(Pageable pageable){
        return ResponseEntity.ok().body(hospitalService.getHospitals(pageable));
    }

    @GetMapping("/{id}") //병원 한곳
    public ResponseEntity<HospitalResponse> getHospital(@PathVariable Integer id) { // ResponseEntity도 DTO타입
        HospitalResponse hospitalResponse = hospitalService.getHospital(id); // DTO
        return ResponseEntity.ok().body(hospitalResponse); // Return은 DTO로
    }

    @PostMapping("/{id}/reviews") //병원에 리뷰 추가하기
    public ResponseEntity<ReviewResponse> addHospitalReviews(@PathVariable Integer id, @RequestBody ReviewRequest reviewCreateRequest) {
        return ResponseEntity.ok().body(reviewService.add(reviewCreateRequest));
    }

    @GetMapping("/{id}/reviews") //병원 id에 달린 모든 리뷰
    public ResponseEntity<List<ReviewResponse>> getHospitalReviews(@PathVariable Integer id) {
        return ResponseEntity.ok().body(reviewService.getHospitalReviews(id));
    }
}
