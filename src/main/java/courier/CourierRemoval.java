package courier;

import lombok.Data;

@Data
public class CourierRemoval {
    private String courierId;

    public CourierRemoval(String courierId) {
        this.courierId = courierId;
    }
}