package issac.issac_server.post.domain.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberTemplate;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import issac.issac_server.common.domain.EntityStatus;
import issac.issac_server.post.application.dto.PostSearchCondition;
import issac.issac_server.post.domain.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

import static issac.issac_server.post.domain.QPost.post;

@RequiredArgsConstructor
public class PostRepositoryCustomImpl implements PostRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Post> findPosts(PostSearchCondition condition, Pageable pageable) {
        JPAQuery<Post> contentQuery = queryFactory
                .selectFrom(post).distinct()
                .where(filterByCondition(condition))
                .orderBy(orderByCondition(pageable.getSort()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        JPAQuery<Long> countQuery = queryFactory
                .select(post.countDistinct())
                .from(post)
                .where(filterByCondition(condition));

        List<Post> posts = contentQuery.fetch();

        return PageableExecutionUtils.getPage(posts, pageable, countQuery::fetchOne);
    }
    private BooleanBuilder filterByCondition(PostSearchCondition condition) {

        BooleanBuilder builder = new BooleanBuilder();

        return builder
                .and(entityStatusIsActive())
                .and(keywordContain(condition.getKeyword()));
    }
    private OrderSpecifier<?>[] orderByCondition(Sort sort) {
        return new OrderSpecifier[]{
                new OrderSpecifier<>(Order.DESC, post.id)
        };
    }

    private BooleanExpression entityStatusIsActive() {
        return post.entityStatus.eq(EntityStatus.ACTIVE);
    }


    private BooleanExpression keywordContain(String keyword) {
        return StringUtils.hasText(keyword) ? post.title.contains(keyword).or(post.content.contains(keyword)) : null;
    }

    public BooleanExpression keywordSearch(String word) {
        NumberTemplate booleanTemplate = Expressions.numberTemplate(Double.class,
                "function('match',{0},{1})", post.title, word);

        return booleanTemplate.gt(0);
    }
}
