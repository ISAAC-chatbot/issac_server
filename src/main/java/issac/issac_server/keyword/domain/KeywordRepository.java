package issac.issac_server.keyword.domain;

import issac.issac_server.user.domain.University;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

@EnableJpaRepositories
public interface KeywordRepository extends JpaRepository<Keyword, Long>, PagingAndSortingRepository<Keyword, Long> {

    Optional<Keyword> findByText(String text);

    Page<Keyword> findAll(Pageable pageable);

    // spring batch -> KeywordJob
    @Query(
            value = "SELECT DISTINCT k.text FROM Keyword k WHERE k.university = :university",
            countQuery = "SELECT COUNT(DISTINCT k.text) FROM Keyword k WHERE k.university = :university"
    )
    Page<String> findDistinctTextsByUniversity(University university, Pageable pageable);


    List<Keyword> findByUserId(Long userId);

    boolean existsByUserIdAndText(Long userId, String text);

    List<Keyword> findByUniversityAndText(University university, String text);

    void deleteAllByUserId(Long userId);
}
