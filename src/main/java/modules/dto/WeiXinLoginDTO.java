package modules.dto;

import lombok.Data;

/**
 * @author xieyihong
 */

@Data
public class WeiXinLoginDTO {
    private String code;
    private String phone;
    private String encryptedData;
    private String iv;
    private String session_key;
}
