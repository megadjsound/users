package skillbox.com.users.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "subscriptions", schema = "users_scheme")
public class SubscriptionEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    @Column(name = "subscribe_date", nullable = false)
    private LocalDate subscribeDate = LocalDate.now();

    @Column(name = "subscriber_id", nullable = false)
    private Integer subscriberId;

    @Column(name = "subscribed_id", nullable = false)
    private Integer subscribedId;

    public SubscriptionEntity() {
    }

    public SubscriptionEntity(LocalDate subscribeDate, Integer subscriberId, Integer subscribedId) {
        this.subscribeDate = subscribeDate;
        this.subscriberId = subscriberId;
        this.subscribedId = subscribedId;
    }

    public Integer getId() {
        return id;
    }

    public LocalDate getSubscribeDate() {
        return subscribeDate;
    }

    public void setSubscribeDate(LocalDate subscribeDate) {
        this.subscribeDate = subscribeDate;
    }

    public Integer getSubscriberId() {
        return subscriberId;
    }

    public void setSubscriberId(Integer subscriberId) {
        this.subscriberId = subscriberId;
    }

    public Integer getSubscribedId() {
        return subscribedId;
    }

    public void setSubscribedId(Integer subscribedId) {
        this.subscribedId = subscribedId;
    }

    @Override
    public String toString() {
        return "SubscriptionEntity{" +
                "id=" + id +
                ", subscribeDate=" + subscribeDate +
                ", subscriberId=" + subscriberId +
                ", subscribedId=" + subscribedId +
                '}';
    }
}
