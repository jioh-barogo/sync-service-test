package dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CreateResponse {

  private String partnerOrderId;
  private String orderAgencyOrderId;
  private String orderAgencyTodayOrderId;
  private String partnerStoreId;
  private String dropJibunAddress;
  private String dropRoadAddress;
  private String dropAddressDetail;
  private String dropLocation;
  private String dropAddressInfo;
  private String customerPhone;
  private String ordererSubPhone;
  private String customerName;
  private String receiverPhone;
  private String receiverSubPhone;
  private String receiverName;
  private Long pickupExpectedAt;
  private Boolean isReservation;
  private Long dropExpectedAt;
  private Long reservedOrderDisplayTime;
  private Integer totalPayPrice;
  private BigDecimal orderAmount;
  private BigDecimal prePayAmount;
  private Integer deferredPrice;
  private BigDecimal payCashAmount;
  private BigDecimal payCardAmount;
  private BigDecimal taxFreePayAmount;
  private String goodsNames;
  private List<String> orderGoods;
  private String storeOrderMemo;
  private String memo;
  private Long orderAgencyOrderCreatedAt;
  private String orderChannel;
  private Boolean isUntact;
  private Boolean shouldScanQRCodeForPickup;
  private Long timeOutAt;
}