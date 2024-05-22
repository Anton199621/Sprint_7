package courier;

import lombok.Data;

@Data
public class CourierRemoval {
    private String courierId;

    public CourierRemoval(String courierId) {
        this.courierId = courierId;
    }

    public String getCourierId() {
        return courierId;
    }

    public void setCourierId(String courierId) {
        this.courierId = courierId;
    }
}