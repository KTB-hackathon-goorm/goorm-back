package dev.ktb_hackathon.goorm.plogging.service;

import dev.ktb_hackathon.goorm.member.entity.MemberEntity;
import dev.ktb_hackathon.goorm.member.exception.NotFoundMemberException;
import dev.ktb_hackathon.goorm.member.repository.MemberJpaRepository;
import dev.ktb_hackathon.goorm.plogging.entity.PloggingEntity;
import dev.ktb_hackathon.goorm.plogging.repository.PloggingJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PloggingService {

    // fixme-noah: image path 변경될 수 있음
    private final String PLOGGING_PATH = "/src/main/resources/public/images/";

    private final MemberJpaRepository memberJpaRepository;
    private final PloggingJpaRepository ploggingJpaRepository;

    public List<PloggingEntity> searchByPageNumber(int pageNumber) {
        return ploggingJpaRepository
                .findAllToSliceBy(PageRequest.of(pageNumber, 5, Sort.by("id")))
                .getContent();
    }

    @Transactional
    public void register(String memberEmail, double latitude, double longitude, MultipartFile image, String content) {
        MemberEntity foundMemberEntity = memberJpaRepository.findByEmail(memberEmail)
                .orElseThrow(NotFoundMemberException::new);

        String imageName = generateImageName(image);

        // 절대 경로로 변환 (프로젝트 루트 기준)
        String absoluteImagePath = System.getProperty("user.dir") + File.separator + PLOGGING_PATH + imageName;

        // 데이터베이스에 저장할 경로
        String imageUrl = "/images/" + imageName;

        ploggingJpaRepository
                .save(new PloggingEntity(foundMemberEntity, latitude, longitude, "http://localhost:8080" + imageUrl, content));

        // 디렉터리 존재 확인 및 생성
        File directory = new File(PLOGGING_PATH);

        if (!directory.exists()) {
            directory.mkdirs();
        }

        // 이미지 저장
        try {
            image.transferTo(new File(absoluteImagePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateImageName(MultipartFile image) {
        return UUID.randomUUID() + getImageExtension(StringUtils.cleanPath(Objects.requireNonNull(image.getOriginalFilename())));
    }

    private String getImageExtension(String imageName) {
        String extension = "";

        int index = imageName.lastIndexOf('.');

        if (index > 0) {
            extension = imageName.substring(index);
        }

        return extension;
    }
}
