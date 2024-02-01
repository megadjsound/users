package skillbox.com.users.dto;

import java.time.LocalDate;

public class SubscriptionDto {
    private Integer id;
    private LocalDate subscribeDate = LocalDate.now();
    private Integer subscriberId;
    private Integer subscribedId;

    public SubscriptionDto() {
    }

    public SubscriptionDto(Integer id, LocalDate subscribeDate, Integer subscriberId, Integer subscribedId) {
        this.id = id;
        this.subscribeDate = subscribeDate;
        this.subscriberId = subscriberId;
        this.subscribedId = subscribedId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}
