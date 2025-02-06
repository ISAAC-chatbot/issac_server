package issac.issac_server.notification.domain.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import issac.issac_server.notification.application.dto.NotificationSearchCondition;
import issac.issac_server.notification.domain.Notification;
import issac.issac_server.notification.domain.NotificationType;
import issac.issac_server.reaction.domain.TargetType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

import static issac.issac_server.notification.domain.QNotification.notification;

@RequiredArgsConstructor
public class NotificationRepositoryCustomImpl implements NotificationRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Notification> findNotifications(Long userId, NotificationSearchCondition condition, Pageable pageable) {
        JPAQuery<Notification> contentQuery = queryFactory
                .selectFrom(notification).distinct()
                .where(filterByCondition(userId, condition))
                .orderBy(orderByCondition(pageable.getSort()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        JPAQuery<Long> countQuery = queryFactory
                .select(notification.countDistinct())
                .from(notification)
                .where(filterByCondition(userId, condition));

        List<Notification> notifications = contentQuery.fetch();

        return PageableExecutionUtils.getPage(notifications, pageable, countQuery::fetchOne);
    }

    private BooleanBuilder filterByCondition(Long userId, NotificationSearchCondition condition) {

        BooleanBuilder builder = new BooleanBuilder();

        return builder
                .and(isUserNotification(userId))
                .and(notificationTypeEq(condition.getNotificationType()))
                .and(entityTypeEq(condition.getEntityType()))
                .and(notificationIsRead(condition.getRead()));
    }

    private OrderSpecifier<?>[] orderByCondition(Sort sort) {
        return new OrderSpecifier[]{
                new OrderSpecifier<>(Order.DESC, notification.id)
        };
    }

    private BooleanExpression isUserNotification(Long userId) {
        return userId != null ? notification.userId.eq(userId) : null;
    }

    private BooleanExpression notificationTypeEq(NotificationType type) {
        return type != null ? notification.notificationType.eq(type) : null;
    }

    private BooleanExpression entityTypeEq(TargetType type) {
        return type != null ? notification.entityType.eq(type) : null;
    }

    private BooleanExpression notificationIsRead(Boolean read) {
        return read != null ? notification.readStatus.eq(read) : null;
    }


}
