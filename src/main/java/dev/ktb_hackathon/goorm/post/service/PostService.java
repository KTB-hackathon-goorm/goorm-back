package dev.ktb_hackathon.goorm.post.service;

import dev.ktb_hackathon.goorm.member.entity.MemberEntity;
import dev.ktb_hackathon.goorm.member.exception.NotFoundMemberException;
import dev.ktb_hackathon.goorm.member.repository.MemberJpaRepository;
import dev.ktb_hackathon.goorm.post.domain.Location;
import dev.ktb_hackathon.goorm.post.entity.PostEntity;
import dev.ktb_hackathon.goorm.post.repository.PostJpaRepository;
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
public class PostService {

    // fixme-noah: image path 변경될 수 있음
    private final String POST_PATH = "/src/main/resources/public/images/";

    private final MemberJpaRepository memberJpaRepository;
    private final PostJpaRepository postJpaRepository;

    public List<PostEntity> searchByPageNumber(int pageNumber) {
        return postJpaRepository
                .findAllToSliceBy(PageRequest.of(pageNumber, 5, Sort.by("id")))
                .getContent();
    }

    @Transactional
    public void register(String memberEmail, double latitude, double longitude, MultipartFile image, String content, String location) {
        MemberEntity foundMemberEntity = memberJpaRepository.findByEmail(memberEmail)
                .orElseThrow(NotFoundMemberException::new);

        String imageName = generateImageName(image);

        // 절대 경로로 변환 (프로젝트 루트 기준)
        String absoluteImagePath = System.getProperty("user.dir") + File.separator + POST_PATH + imageName;

        // 데이터베이스에 저장할 경로
        String imageUrl = "/images/" + imageName;

        postJpaRepository
                .save(new PostEntity(foundMemberEntity, latitude, longitude, "http://localhost:8080" + imageUrl, content, getLocation(location)));

        // 디렉터리 존재 확인 및 생성
        File directory = new File(POST_PATH);

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

    private Location getLocation(String location) {
        return switch (location) {
            case "한경" -> Location.HANGYEONG;
            case "한림" -> Location.HALLIM;
            case "애월" -> Location.AEWOL;
            case "제주" -> Location.JEJU;
            case "조천" -> Location.JOCHEON;
            case "구좌" -> Location.GUJWA;
            case "대정" -> Location.DAEJEONG;
            case "안덕" -> Location.ANDEOK;
            case "중문" -> Location.JUNGMUN;
            case "서귀포" -> Location.SEOGWIPO;
            case "남원" -> Location.NAMWON;
            case "표선" -> Location.PYOSEON;
            case "성산" -> Location.SONGSAN;
            default -> Location.JEJU;
        };
    }
}
